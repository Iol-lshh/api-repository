package lshh.apirepository.common.resourcer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.experimental.Accessors;
import lshh.apirepository.common.dbhelper.PlainTextQueryManager;
import lshh.apirepository.common.dbhelper.QueryManager;
import lshh.apirepository.common.dbhelper.QueryStatement;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.dto.request.QueryMsgDto;

@Accessors(fluent = true)
@Getter
public class JdbcResourcerContext implements ResourcerContext{

    int id;
    Type type;
    String name;
    long started;
    DataSource dataSource;

    QueryManager queryManager;

    public JdbcResourcerContext(int id, Type type, String name, DataSource dataSource){
        
        this.id=id;
        this.type=type;
        this.name=name;
        this.started=LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.dataSource=dataSource;

        this.queryManager = new PlainTextQueryManager(dataSource);
    }

    public List<Map<String, Object>> getResource(QueryMsgDto dto) throws Exception {
        
        QueryStatement statement = queryManager.createStatement()
            .setQuery(dto.query());
            
        for(QueryArgumentDto<Object> arg:dto.arguments()){
            statement.addParam(arg.name(), arg.value());
        }
        return statement.query();
    }

    public <T> List<T> getResource(QueryMsgDto dto, Class<T> requiredType) throws Exception {
        
        QueryStatement statement = queryManager.createStatement()
            .setQuery(dto.query());

        for(QueryArgumentDto<Object> arg:dto.arguments()){
            statement.addParam(arg.name(), arg.value());
        }
        return statement.queryByClass(requiredType);
    }

}