package lshh.apirepository.service.api.router;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;
import lshh.apirepository.service.api.query.QueryService;

@Service
public class RouterServiceJpa implements RouterService {

    @Autowired
    RouterRepository routerRepository;
    @Autowired
    QueryRepository queryRepository;

    @Transactional
    public RouterDto toDto(Router entity){
        return new RouterDto()
            .id(entity.id())
            .name(entity.name())
            .path(entity.path())
            .description(entity.description())
            .isDisabled(entity.isDisabled())
            .queryId(entity.query().id())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());       
    }

    public Router toEntity(RouterDto dto){
        Router result = new Router()
            .id(dto.id())
            .name(dto.name())
            .path(dto.path())
            .description(dto.description())
            .isDisabled(dto.isDisabled());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    @Transactional
    public Status save(RouterDto dto) {
        Router router;
        
        if(dto.id() == null){
            dto.created(LocalDateTime.now());
            router = toEntity(dto);
        }else{
            router = findEntity(dto.id())
                .orElseGet(()->{
                    dto.created(LocalDateTime.now());
                    return toEntity(dto);
                });
        }

        Query query = null;
        if(dto.queryId() != null){
            query = queryRepository.findById(dto.queryId()).orElse(null);
        }
        
        router.name(dto.name()!=null?dto.name():router.name())
            .path(dto.path()!=null?dto.path():router.path())
            .description(dto.description()!=null?dto.description():router.description())
            .isDisabled(dto.isDisabled())
            .query(query)
            .setEnabled(dto.isEnabled());

        if(dto.deleted()!=null){
            router.setDeleted(dto.deleted());
        }

        routerRepository.save(router);
        return Status.OK;
    }

    @Override
    public List<RouterDto> findList(int pageSize, int pageNo) {
        return findEntityList(pageSize, pageNo).stream().map(this::toDto).toList();
    }

    @Override
    public Optional<RouterDto> find(int id) {
        return findEntity(id).map(this::toDto);
    }

    @Override
    public List<RouterDto> findAll(){
        return findEntityAll().stream().map(this::toDto).toList();
    }

    @Transactional
    public Optional<Router> findEntity(int id) {
        return routerRepository.findById(id);
    }
    
    @Transactional
    public List<Router> findEntityList(int pageSize, int pageNo) {
        return routerRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNo)).toList();
    }

    @Transactional
    public List<Router> findEntityAll(){
        return routerRepository.findAll();
    }
}
