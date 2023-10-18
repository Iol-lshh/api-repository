package lshh.apirepository.service.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.orm.api.resource.QueryParameter;
import lshh.apirepository.orm.api.resource.QueryParameterRepository;

@Service
public class QueryParameterServiceJpa implements QueryParameterService{

    @Autowired
    QueryParameterRepository queryParameterRepository;

    public QueryParameterDto toDto(QueryParameter entity) {
        return new QueryParameterDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .type(entity.type())
            .isOptional(entity.isOptional());    
    }

    public QueryParameter toEntity(QueryParameterDto dto){
        return new QueryParameter()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .type(dto.type())
            .isOptional(dto.isOptional());    
    }

    @Transactional
    @Override
    public Status save(QueryParameterDto dto) {
        QueryParameter entity = toEntity(dto);
        queryParameterRepository.save(entity);
        return Status.OK;
    }
}
