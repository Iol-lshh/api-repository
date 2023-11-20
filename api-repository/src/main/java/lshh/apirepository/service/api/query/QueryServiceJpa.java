package lshh.apirepository.service.api.query;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;
import lshh.apirepository.orm.api.router.RouterRepository;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@Service
public class QueryServiceJpa implements QueryService{

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    ResourcerInfoRepository resourcerInfoRepository;
    @Autowired
    RouterRepository routerRepository;
    @Autowired
    ResourcerService resourcerService;

    public QueryDto toDto(Query entity){
        return new QueryDto()
            .id(entity.id())
            .name(entity.name())
            .contents(entity.contents())
            .description(entity.description())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .resourcerId(entity.resourcerId())
            .isEnabled(entity.isEnabled());
    }
    public Query toEntity(QueryDto dto){
        Query result = new Query()
            .id(dto.id())
            .name(dto.name())
            .contents(dto.contents())
            .description(dto.description())
            .resourcerId(dto.resourcerId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        
        return result;
    }

    @Override
    public List<QueryDto> findListByResource(int resourceId) {

        Optional<ResourcerInfo> maybeResourcer = resourcerInfoRepository.findById(resourceId);
        if(maybeResourcer.isEmpty()){
            return List.of();
        }
        return queryRepository.findByResourcerId(maybeResourcer.get().id())
            .stream().map(this::toDto).toList();
    }

    @Override
    public Optional<QueryDto> find(int id) {
        return queryRepository.findById(id).map(this::toDto);
    }
    
    @Override
    public Status save(QueryDto dto) throws Exception {
        Query entity;

        if(dto.id()==null){
            entity = new Query();
            entity.setCreated(LocalDateTime.now());
        }else{
            entity = queryRepository.findById(dto.id())
                .orElseGet(()->{
                    Query _entity = new Query();
                    _entity.setCreated(LocalDateTime.now());
                    return _entity;
                });
        }
        
        entity
            .name(dto.name()!=null?dto.name():entity.name())
            .contents(dto.contents()!=null?dto.contents():entity.contents())
            .description(dto.description()!=null?dto.description():entity.description())
            .setEnabled(dto.isEnabled());

        if(dto.deleted()!=null){
            entity.setDeleted(dto.deleted());
        }

        ResourcerInfo resourcer = resourcerInfoRepository.findById(dto.resourcerId())
            .orElseThrow(()->new Exception("resourcer 참조 오류"));
        entity.resourcerId(resourcer.id());

        queryRepository.save(entity);

        return Status.OK;
    }
}
