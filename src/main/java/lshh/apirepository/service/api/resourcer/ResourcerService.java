package lshh.apirepository.service.api.resourcer;

import java.util.List;
import java.util.Optional;

import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.ServiceTemplate;

public interface ResourcerService extends ServiceTemplate{
    Optional<ResourcerDto> findByPath(String path);
 
    List<ResourcerDto> findList(int pageSize, int pageNo);

    Status save(ResourcerDto resourcer);

    Optional<ResourcerDto> find(int id);
}
