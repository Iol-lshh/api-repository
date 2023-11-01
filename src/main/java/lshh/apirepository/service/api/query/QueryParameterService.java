package lshh.apirepository.service.api.query;

import java.util.List;

import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.service.ServiceTemplate;

public interface QueryParameterService extends ServiceTemplate{
     Status save(QueryParameterDto dto) throws Exception;
     Status save(List<QueryParameterDto> dtos) throws Exception;
     List<QueryParameterDto> findList(int queryId);
}
