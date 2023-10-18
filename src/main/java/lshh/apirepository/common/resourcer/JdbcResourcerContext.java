package lshh.apirepository.common.resourcer;

import org.springframework.jdbc.core.JdbcTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JdbcResourcerContext implements ResourcerContext{

    final int id;
    final String type;
    final long started;

    final JdbcTemplate jdbcTemplate;

    @Override
    public Object getResource(Object... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }

    @Override
    public <T> T getResource(Class<T> requiredType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }

    @Override
    public <T> T getResource(Class<T> requiredType, Object... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }
    
}
