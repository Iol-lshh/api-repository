package lshh.apirepository.service.api.router;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.PipelineViewDto;
import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.dto.api.RouterViewDto;
import lshh.apirepository.orm.api.pipeline.PipelineInfo;
import lshh.apirepository.orm.api.pipeline.PipelineInfoRepository;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;
import lshh.apirepository.service.api.pipeline.PipelineService;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@Service
public class RouterServiceJpa implements RouterService {

    @Autowired
    RouterRepository routerRepository;
    @Autowired
    PipelineInfoRepository pipelineInfoRepository;
    @Autowired
    ResourcerService resourcerService;
    @Autowired
    PipelineService pipelineService;

    @Transactional
    public RouterDto toDto(Router entity){
        return new RouterDto()
            .id(entity.id())
            .name(entity.name())
            .path(entity.path())
            .description(entity.description())
            .pipelineId(entity.pipelineId())
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
            .pipelineId(dto.pipelineId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Status save(RouterDto dto) {
        Router entity;
        
        if(dto.id() == null){
            entity = new Router();
            entity.setCreated(LocalDateTime.now());
        }else{
            entity = routerRepository.findById(dto.id())
                .orElseGet(()->{
                    Router _entity = new Router();
                    _entity.setCreated(LocalDateTime.now());
                    return _entity;
                });
        }

        PipelineInfo pipelineInfo = null;
        if(dto.pipelineId() != null){
            pipelineInfo = pipelineInfoRepository.findById(dto.pipelineId()).orElse(null);
        }
        
        entity
            .name(dto.name()!=null?dto.name():entity.name())
            .path(dto.path()!=null?dto.path():entity.path())
            .description(dto.description()!=null?dto.description():entity.description())
            .pipelineId(pipelineInfo.id())
            .setEnabled(dto.isEnabled());

        if(dto.deleted()!=null){
            entity.setDeleted(dto.deleted());
        }

        routerRepository.save(entity);
        return Status.OK;
    }

    @Override
    public List<RouterDto> findList(int pageSize, int pageNo) {
        return routerRepository
            .findAll(Pageable.ofSize(pageSize).withPage(pageNo))
            .map(this::toDto).toList();
    }

    @Override
    public Optional<RouterDto> find(int id) {
        return routerRepository
            .findById(id)
            .map(this::toDto);
    }

    @Override
    public List<RouterDto> findAll(){
        return routerRepository
            .findAll()
            .stream().map(this::toDto).toList();
    }

    @Override
    public RouterViewDto findView(int id) throws Exception{

        RouterDto routerDto = find(id).orElse(null);

        PipelineViewDto pipelineViewDto = null;
        if(routerDto != null){
            pipelineViewDto = pipelineService.findView(routerDto.id());
        }

        return new RouterViewDto()
            .router(routerDto)
            .pipeline(pipelineViewDto);
    }

    @Override
    public Optional<RouterDto> findByPath(String path){
        return routerRepository.findByPath(path).map(this::toDto);
    }
}
