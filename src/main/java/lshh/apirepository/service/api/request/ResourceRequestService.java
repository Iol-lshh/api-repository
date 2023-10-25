package lshh.apirepository.service.api.request;

import java.util.Optional;
import org.springframework.stereotype.Service;

import lshh.apirepository.dto.request.ResourceRequestDto;
import lshh.apirepository.service.ServiceTemplate;

@Service
public interface ResourceRequestService extends ServiceTemplate {

    Optional<Object> getByPath(String path) throws Exception;
    Optional<Object> get(ResourceRequestDto dto) throws Exception;
}
