package lshh.apirepository.service.api.router;

import java.util.List;
import java.util.Optional;

import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.service.ServiceTemplate;

public interface RouterService extends ServiceTemplate{
    Status save(RouterDto dto);
    
    List<RouterDto> findList(int pageSize, int pageNo);
    Optional<RouterDto> find(int id);
    List<RouterDto> findAll();
}
