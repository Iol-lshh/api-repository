package lshh.apirepository.common.dbhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class PlainTextQueryManager implements QueryManager{
    
    PlainTextQueryFactory factory;

    public PlainTextQueryManager(DataSource dataSource){
        this.factory = new PlainTextQueryFactory(dataSource);
    }
    
    @Override
    public QueryFactory factory() {
        return this.factory();
    }

    @Override
    public PlainTextQueryStatement createStatement() {
        return this.factory.createStatement();
    }

    //
    public class PlainTextQueryFactory implements QueryFactory{
        DataSource dataSource;

        PlainTextQueryFactory(DataSource dataSource){
            this.dataSource = dataSource;
        }

        @Override
        public PlainTextQueryStatement createStatement() {
            return new PlainTextQueryStatement(dataSource);
        }
    }

    //
    public class PlainTextQueryStatement implements QueryStatement{
        DataSource dataSource;
        String queryText;
        List<Parameter> params;

        PlainTextQueryStatement(DataSource dataSource){
            this.dataSource = dataSource;
            this.params = new ArrayList<>();
        }

        //// 쿼리 제어
        @Override
        public QueryStatement setQuery(String query) {
            this.queryText = query;
            return this;
        }

        //// 파라미터 제어
        @Override
        public QueryStatement clearParams() {
            this.params.clear();
            return this;
        }

        @Override
        public QueryStatement addParam(String key, Object val) {
            this.params.add(new Parameter(key, val));
            return this;
        }

        //// 실행 제어
        @Override
        public List<Map<String, Object>> query() throws Exception{
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
            MapSqlParameterSource parameters = toMapSqlParameterSource();
            
            return jdbcTemplate.queryForList(queryText, parameters);
        } 

        @Override
        public <T> List<T> queryByClass(Class<T> theClass) throws Exception {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
            MapSqlParameterSource parameters = toMapSqlParameterSource();
            
            return jdbcTemplate.queryForList(this.queryText, parameters, theClass);
        }

        @Override
        public Status command() throws Exception {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
            MapSqlParameterSource parameters = toMapSqlParameterSource();
            int result = jdbcTemplate.update(this.queryText, parameters);
            return result > 0 ? Status.OK : Status.FAIL;
        }

        public MapSqlParameterSource toMapSqlParameterSource(){
            MapSqlParameterSource parameters = new MapSqlParameterSource();

            for(Parameter p : this.params){
                parameters.addValue(p.key, p.val);
            }
            return parameters;
        }
    }

}
