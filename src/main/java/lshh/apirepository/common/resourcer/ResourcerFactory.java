package lshh.apirepository.common.resourcer;

import javax.sql.DataSource;

public interface ResourcerFactory{
    ResourcerContext initResourcer(int id, String name, DataSource dataSource) throws Exception;

    boolean existsResourcer(int id);
}
