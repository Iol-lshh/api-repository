package lshh.apirepository.service.api.query;

import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.service.ServiceTemplate;

public interface QueryParameterService extends ServiceTemplate{
     Status save(QueryParameterDto dto) throws Exception;
}
