package lshh.apirepository.service.api.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;
import lshh.apirepository.common.dbhelper.QueryStatement.Parameter.IoType;
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

    @Transactional
    public QueryParameterDto toDto(QueryParameter entity) {
        return new QueryParameterDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .ioType(IoType.valueOf(entity.ioType()))
            .isOptional(entity.isOptional())
            .queryId(entity.query().id());
    }

    public QueryParameter toEntity(QueryParameterDto dto){
        return new QueryParameter()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .ioType(dto.ioType().name())
            .isOptional(dto.isOptional());
    }

    @Override
    @Transactional
    public Status save(QueryParameterDto dto) throws Exception {
        QueryParameter entity;
        
        if(dto.id()==null){
            dto.created(LocalDateTime.now());
            entity = toEntity(dto);
        }else{
            entity = findEntity(dto.id())
                .orElseGet(()->{
                    dto.created(LocalDateTime.now());
                    return toEntity(dto);
                });
        }

        Query query = queryRepository.findById(dto.queryId())
            .orElseThrow(()->new Exception("잘못된 query"));
        entity.query(query);

        queryParameterRepository.save(entity);
        return Status.OK;
    }

    public Optional<QueryParameter> findEntity(int id){
        return queryParameterRepository.findById(id);
    }
}
