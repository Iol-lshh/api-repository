package lshh.apirepository.service.api.resourcer;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;

@Service
public class ResourcerServiceJpa implements ResourcerService{
    
    @Autowired
    ResourcerInfoRepository resourcerRepository;

    public ResourcerDto toDto(ResourcerInfo resourcer){
        return new ResourcerDto()
            .id(resourcer.id())
            .name(resourcer.name())
            .path(resourcer.path())
            .description(resourcer.description())
            .key(resourcer.key());
    }

    public ResourcerInfo toEntity(ResourcerDto dto){
        return new ResourcerInfo()
            .id(dto.id())
            .name(dto.name())
            .path(dto.path())
            .description(dto.description())
            .key(dto.key());
    }

    @Override
    public Optional<ResourcerDto> findByPath(String path) {
        return findEntity(path).map(e->toDto(e));
    }

    @Override
    public Optional<ResourcerDto> find(int id){
        return findEntity(id).map(e->toDto(e));
    }

    @Override
    public List<ResourcerDto> findList(int pageSize, int pageNo) {
       return findEntityList(pageSize, pageNo).stream().map(e->toDto(e)).toList();
    }

    @Override
    public Status save(ResourcerDto dto) {
        resourcerRepository.save(toEntity(dto));
        return Status.OK;
    }

    
    ////////////////////////////////////////////////////////////

    @Transactional
    public Optional<ResourcerInfo> findEntity(String path){
        return resourcerRepository.findOneByPath(path);
    }

    @Transactional
    public List<ResourcerInfo> findEntityList(int pageSize, int pageNo){
        return resourcerRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNo)).toList();
    }

    @Transactional
    public Optional<ResourcerInfo> findEntity(int id){
        return resourcerRepository.findById(id);
    }

}
