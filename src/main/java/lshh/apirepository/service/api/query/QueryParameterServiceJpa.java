package lshh.apirepository.service.api.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryParameter;
import lshh.apirepository.orm.api.query.QueryParameterRepository;
import lshh.apirepository.orm.api.query.QueryRepository;

@Service
public class QueryParameterServiceJpa implements QueryParameterService{

    @Autowired
    QueryParameterRepository queryParameterRepository;
    @Autowired
    QueryRepository queryRepository;

    public QueryParameterDto toDto(QueryParameter entity) {
        return new QueryParameterDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .ioType(entity.ioType())
            .isOptional(entity.isOptional())
            .queryId(entity.queryId())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());
    }

    public QueryParameter toEntity(QueryParameterDto dto){
        QueryParameter result = new QueryParameter()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .ioType(dto.ioType())
            .isOptional(dto.isOptional())
            .queryId(dto.queryId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    @Transactional
    public Status save(QueryParameterDto dto) throws Exception {
        QueryParameter entity;
        
        if(dto.id()==null){
            dto.created(LocalDateTime.now());
            entity = toEntity(dto);
        }else{
            entity = queryParameterRepository.findById(dto.id())
                .orElseGet(()->{
                    dto.created(LocalDateTime.now());
                    return toEntity(dto);
                });
        }

        Query query = queryRepository.findById(dto.queryId())
            .orElseThrow(()->new Exception("잘못된 query"));
        entity.queryId(query.id());

        queryParameterRepository.save(entity);
        return Status.OK;
    }

    @Override
    @Transactional
    public Status save(List<QueryParameterDto> dtos) throws Exception {
        queryParameterRepository.saveAll(dtos.stream().map(this::toEntity).toList());
        return Status.OK;
    }

    @Override
    public List<QueryParameterDto> findList(int queryId) {

        return queryParameterRepository.findByQueryId(queryId)
            .stream().map(this::toDto).toList();
    }
}
