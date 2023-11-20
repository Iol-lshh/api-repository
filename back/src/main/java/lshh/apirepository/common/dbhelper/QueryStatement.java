package lshh.apirepository.common.dbhelper;

import java.util.List;
import java.util.Map;

public interface QueryStatement {
    
    public enum Status{WAIT, OK, FAIL}
    
    public class Parameter{
        public enum IoType{INPUT, OUTPUT}

        String key;
        Object val;
        IoType type;

        public Parameter(String key, Object val){
            this.key = key;
            this.val = val;
            this.type = IoType.INPUT;
        }

        public Parameter(String key, Object val, IoType type){
            this.key = key;
            this.val = val;
            this.type = type;
        }
    }

    QueryStatement setQuery(String query);

    QueryStatement clearParams();
    QueryStatement addParam(String key, Object val);
    List<Map<String, Object>> query() throws Exception;
    <T> List<T> queryByClass(Class<T> theClass) throws Exception;
    Status command() throws Exception;
}
