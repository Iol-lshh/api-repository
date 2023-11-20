package lshh.apirepository.service.api.query;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.dto.api.QueryViewDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryParameter;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;

@Service
public class QueryServiceJpa implements QueryService{

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    ResourcerInfoRepository resourcerInfoRepository;
    @Autowired
    RouterRepository routerRepository;
    @Autowired
    QueryParameterServiceJpa queryParameterService;

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
    public Optional<QueryDto> findByRouter(int routerId) {

        Optional<Router> maybeRouter = routerRepository.findById(routerId);
        if(maybeRouter.isEmpty()){
            return Optional.empty();
        }
        return queryRepository.findById(maybeRouter.get().queryId())
            .map(this::toDto);
    }

    @Override
    public Optional<QueryDto> find(int id) {
        return queryRepository.findById(id).map(this::toDto);
    }

    @Override
    public QueryViewDto findView(int id) throws Exception{
        Optional<Query> maybeQuery = queryRepository.findById(id);
        QueryDto queryDto = maybeQuery.map(this::toDto).orElseThrow(()->new Exception("no Query"));
 
        if(queryDto.id() ==null){
            return new QueryViewDto();
        }

        List<QueryParameterDto> parameterDtos = queryParameterService.findList(queryDto.id());
        return new QueryViewDto()
            .query(queryDto)
            .queryParameters(parameterDtos);
    }
    
    @Override
    public Status save(QueryDto dto) throws Exception {
        Query query;

        if(dto.id()==null){
            dto.created(LocalDateTime.now());
            query = toEntity(dto);
        }else{
            query = queryRepository.findById(dto.id())
                .orElseGet(()->{
                    dto.created(LocalDateTime.now());
                    return toEntity(dto);
                });
        }
        
        query
            .name(dto.name()!=null?dto.name():query.name())
            .contents(dto.contents()!=null?dto.contents():query.contents())
            .description(dto.description()!=null?dto.description():query.description())
            .setEnabled(dto.isEnabled());

        if(dto.deleted()!=null){
            query.setDeleted(dto.deleted());
        }

        ResourcerInfo resourcer = resourcerInfoRepository.findById(dto.resourcerId())
            .orElseThrow(()->new Exception("resourcer 참조 오류"));
        query.resourcerId(resourcer.id());

        queryRepository.save(query);

        return Status.OK;
    }
}
