package lshh.apirepository.service.api.query;

import java.util.List;

import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.service.ServiceTemplate;

public interface QueryParameterService extends ServiceTemplate{
     Status save(QueryParameterDto dto) throws Exception;

     Status save(Query query, List<QueryParameterDto> dtos) throws Exception;
}
