package lshh.apirepository.service.api.resourcer;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
        ResourcerInfo resourcer;
        
        if(dto.id()==null){
            dto.created(LocalDateTime.now());
            resourcer = toEntity(dto);
        }else{
            resourcerManager.deallocateResourcer(dto.id());
            resourcer = resourcerRepository.findById(dto.id())
                .orElseGet(()->{
                    dto.created(LocalDateTime.now());
                    return toEntity(dto);
                });
        }

        resourcer
            .name(dto.name()!=null ? dto.name() : resourcer.name())
            .path(dto.path()!=null ? dto.path() : resourcer.path())
            .description(dto.description() !=null ? dto.description() : resourcer.description())
            .accessName(dto.accessName() != null ? dto.accessName():resourcer.accessName())
            .key(dto.key()!=null?dto.key():resourcer.key())
            .driver(dto.driver()!=null?dto.driver():resourcer.driver())
            .driverClassName(dto.driverClassName()!=null?dto.driverClassName():resourcer.driverClassName())
            .setEnabled(dto.isEnabled());
        
        if(dto.deleted()!=null){
            resourcer.setDeleted(dto.deleted());
        }

        resourcerRepository.save(resourcer);
        return Status.OK;
    }
}
