package lshh.apirepository.service.api.resourcer;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lshh.apirepository.common.resourcer.ResourcerManager;
import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;

@Service
public class ResourcerServiceJpa implements ResourcerService{
    
    @Autowired
    ResourcerInfoRepository resourcerRepository;

    ResourcerManager resourcerManager;

    @Override
    public void setResourcerManager(ResourcerManager manager){
        this.resourcerManager = manager;
    }

    public ResourcerDto toDto(ResourcerInfo resourcer){
        return new ResourcerDto()
            .id(resourcer.id())
            .name(resourcer.name())
            .path(resourcer.path())
            .description(resourcer.description())
            .accessName(resourcer.accessName())
            .key(resourcer.key())
            .driver(resourcer.driver())
            .driverClassName(resourcer.driverClassName())
            .created(resourcer.getCreated())
            .deleted(resourcer.getDeleted())
            .isEnabled(resourcer.isEnabled());
    }

    public ResourcerInfo toEntity(ResourcerDto dto){
        ResourcerInfo result = new ResourcerInfo()
            .id(dto.id())
            .name(dto.name())
            .path(dto.path())
            .description(dto.description())
            .accessName(dto.accessName())
            .key(dto.key())
            .driver(dto.driver())
            .driverClassName(dto.driverClassName());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Optional<ResourcerDto> findByPath(String path) {
        return  resourcerRepository.findOneByPath(path).map(this::toDto);
    }

    @Override
    public Optional<ResourcerDto> find(int id){
        return resourcerRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<ResourcerDto> findList(int pageSize, int pageNo) {
        return resourcerRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNo))
            .stream().map(this::toDto).toList();
    }

    @Override
    public List<ResourcerDto> findAll(){
        return resourcerRepository.findAll()
            .stream().map(this::toDto).toList();
    }

    @Override
    public Status save(ResourcerDto dto) {
        ResourcerInfo entity;
        
        if(dto.id()==null){
            entity = new ResourcerInfo();
            entity.setCreated(LocalDateTime.now());
        }else{
            resourcerManager.deallocateResourcer(dto.id());
            entity = resourcerRepository.findById(dto.id())
                .orElseGet(()->{
                    ResourcerInfo _entity = new ResourcerInfo();
                    _entity.setCreated(LocalDateTime.now());
                    return _entity;
                });
        }

        entity
            .name(dto.name()!=null ? dto.name() : entity.name())
            .path(dto.path()!=null ? dto.path() : entity.path())
            .description(dto.description() !=null ? dto.description() : entity.description())
            .accessName(dto.accessName() != null ? dto.accessName():entity.accessName())
            .key(dto.key()!=null?dto.key():entity.key())
            .driver(dto.driver()!=null?dto.driver():entity.driver())
            .driverClassName(dto.driverClassName()!=null?dto.driverClassName():entity.driverClassName())
            .setEnabled(dto.isEnabled());
        
        if(dto.deleted()!=null){
            entity.setDeleted(dto.deleted());
        }

        resourcerRepository.save(entity);
        return Status.OK;
    }
}
