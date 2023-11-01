package lshh.apirepository.service.api.query;

import java.util.List;
import java.util.Optional;

import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.service.ServiceTemplate;

public interface QueryService extends ServiceTemplate{
    List<QueryDto> findListByResource(int resourceId);
    Optional<QueryDto> find(int id);

    Status save(QueryDto query) throws Exception;
}
