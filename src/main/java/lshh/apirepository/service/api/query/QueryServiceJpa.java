package lshh.apirepository.service.api.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.service.api.resourcer.ResourcerServiceJpa;
import lshh.apirepository.service.api.router.RouterServiceJpa;

@Service
public class QueryServiceJpa implements QueryService{

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    ResourcerServiceJpa resourcerService;
    @Autowired
    RouterServiceJpa routerService;
    @Autowired
    QueryParameterServiceJpa queryParameterService;

    public QueryDto toDto(Query entity){
        return new QueryDto()
            .id(entity.id())
            .name(entity.name())
            .contents(entity.contents())
            .description(entity.description())
            .queryParameterList(entity.queryParameters().stream().map(e->queryParameterService.toDto(e)).toList());
    }
    public Query toEntity(QueryDto dto){
        return new Query()
            .id(dto.id())
            .name(dto.name())
            .contents(dto.contents())
            .description(dto.description());
    }

    @Override
    public List<QueryDto> findListByResource(int resourceId) {
        return findEntityByResource(resourceId).stream().map(e->toDto(e)).toList();
    }

    @Override
    public Optional<QueryDto> findByRouter(int routerId) {
        return findEntityListByRouter(routerId).map(e->toDto(e));
    }

    @Override
    @Transactional
    public Status save(QueryDto query) {
        queryRepository.save(toEntity(query));
        query.queryParameterList().forEach(e->queryParameterService.save(e));

        return Status.OK;
    }

    ///////////////////
    @Transactional
    public List<Query> findEntityByResource(int resourceId){
        Optional<ResourcerInfo> maybeResourcer = resourcerService.findEntity(resourceId);

        if(maybeResourcer.isEmpty()){
            return List.of();
        }

        return queryRepository.findByResourcerInfo(maybeResourcer.get());
    }

    @Transactional
    public Optional<Query> findEntityListByRouter(int routerId){
        Optional<Router> maybeRouter = routerService.findEntity(routerId);

        if(maybeRouter.isEmpty()){
            return Optional.empty();
        }

        return queryRepository.findById(maybeRouter.get().query().id());
    }
    
}
