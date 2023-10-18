package lshh.apirepository.common.resourcer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.api.resourcer.ResourcerService;

public class JdbcResourcerManager implements ResourcerManager {
    
    JdbcResourcerFactory resourcerFactory;
    Map<Integer, ResourcerContext> resourcerMap;

    ResourcerService resourcerService;

    public JdbcResourcerManager(Map<Integer, ResourcerContext> resourcerMap, ResourcerService resourcerService){
        this.resourcerFactory = new JdbcResourcerFactory();
        this.resourcerMap = resourcerMap;
        this.resourcerService = resourcerService;
    }

    // # 팩토리
    class JdbcResourcerFactory implements ResourcerFactory{

        @Override
        public ResourcerContext initResourcer(int id, Object jdbcTemplateArg) throws Exception {
            // 다운캐스팅
            if (!(jdbcTemplateArg instanceof JdbcTemplate)){
                throw new Exception("잘못된 인자 타입");
            }
            JdbcTemplate jdbcTemplate = (JdbcTemplate) jdbcTemplateArg;
            
            // 일자
            long nowTimeStamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    
            JdbcResourcerContext jdbcResourcer = new JdbcResourcerContext(
                id,
                "JDBC",
                nowTimeStamp,
                jdbcTemplate
            );

            resourcerMap.put(id, jdbcResourcer);

            return jdbcResourcer;
        }

        @Override
        public boolean existsResourcer(int id) {
            return !resourcerService.find(id).isEmpty();
        }
    }

    // # 가져오기
    @Override
    public ResourcerContext getResourcer(int id) throws Exception {
        if(!this.resourcerFactory.existsResourcer(id)){
            throw new Exception("제공하지 않는 리소서");
        }

        ResourcerContext resourcer = this.resourcerMap.get(id);
        if(resourcer == null){
            ResourcerDto dto = resourcerService.find(id).orElseThrow(()->new Exception("제공하지 않는 리소서"));
            DataSource dataSource = createDataSource(dto);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            resourcer = resourcerFactory.initResourcer(id, jdbcTemplate);
        }
        return resourcer;
    }

    private DataSource createDataSource(ResourcerDto dto) {
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:"+dto.driver()+":"+dto.path());
        config.setUsername(dto.name());
        config.setPassword(dto.key());
        config.setDriverClassName(dto.driverDetail());
        // ex)
        // config.setJdbcUrl("jdbc:postgresql://172.30.1.9:5432/api_repository");
        // config.setUsername("lshh");
        // config.setPassword("lshh")
        // config.setDriverClassName("org.postgresql.Driver");
        
        return new HikariDataSource(config);
    }

    // # 제거하기
    @Override
    public boolean deallocateResourcer(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deallocateResourcer'");
    }

}
