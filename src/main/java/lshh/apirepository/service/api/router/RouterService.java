package lshh.apirepository.service.api.router;

import java.util.List;

import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.service.ServiceTemplate;

public interface RouterService extends ServiceTemplate{
    Status save(RouterDto dto);
    
    List<RouterDto> findList(int pageSize, int pageNo);
    RouterDto find(int id);
}
