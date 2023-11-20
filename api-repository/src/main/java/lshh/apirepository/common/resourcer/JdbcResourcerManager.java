package lshh.apirepository.common.resourcer;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lshh.apirepository.common.resourcer.ResourcerContext.Type;
import lshh.apirepository.dto.api.ResourcerContextDto;
import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@Component
public class JdbcResourcerManager implements ResourcerManager {
    
    ResourcerService resourcerService;
    JdbcResourcerFactory factory;
    Map<Integer, ResourcerContext> resourcerMap;

    // 최초에, 디비로부터 가져와 생성
    public JdbcResourcerManager(@Autowired ResourcerService resourcerService) throws Exception{
        this.resourcerService = resourcerService;
        resourcerService.setResourcerManager(this);
        this.factory = new JdbcResourcerFactory();
        this.resourcerMap = new HashMap<>();
        initMap();
    }

    private void initMap() throws Exception{
        List<ResourcerDto> dtos = this.resourcerService.findAll();
        for(ResourcerDto dto : dtos){
            try{
                DataSource dataSource = createDataSource(dto);
                this.factory.initResourcer(dto.id(), dto.name(), dataSource);
            }catch(Exception e){
                System.out.println("[Create Resourcer Error]: " + dto.id() + ", " + dto.name());
                // 에러난 것은 넘어가기..
            }
        }
    }

    //// # 팩토리
    class JdbcResourcerFactory implements ResourcerFactory{

        @Override
        public ResourcerContext initResourcer(int id, String name, DataSource dataSource) throws Exception {
    
            JdbcResourcerContext jdbcResourcer = new JdbcResourcerContext(
                id,
                Type.DB,
                name,
                dataSource
            );
            return resourcerMap.put(id, jdbcResourcer);
        }

        @Override
        public boolean existsResourcer(int id) {
            return !resourcerService.find(id).isEmpty();
        }
    }

    // # 가져오기
    @Override
    public ResourcerContext getResourcer(int id) throws Exception {
        if(!this.factory.existsResourcer(id)){
            throw new Exception("제공하지 않는 리소서");
        }

        ResourcerContext resourcer = this.resourcerMap.get(id);
        if(resourcer == null){
            ResourcerDto dto = resourcerService.find(id).orElseThrow(()->new Exception("제공하지 않는 리소서"));
            DataSource dataSource = createDataSource(dto);
            resourcer = factory.initResourcer(id, dto.name(), dataSource);
        }
        return resourcer;
    }

    //
    private DataSource createDataSource(ResourcerDto dto) throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:"+dto.driver()+":"+dto.path());
        config.setUsername(dto.accessName());
        config.setPassword(dto.key());
        config.setDriverClassName(dto.driverClassName());
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
        resourcerMap.remove(id);
        return true;
    }

    @Override
    public List<ResourcerContextDto> getContextList() {
        return this.resourcerMap.values().stream()
            .map(e->new ResourcerContextDto()
                .id(e.id())
                .type(e.type())
                .name(e.name())
                .started(e.started())
            ).toList();
    }

}
