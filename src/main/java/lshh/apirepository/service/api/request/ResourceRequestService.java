package lshh.apirepository.service.api.request;

import java.util.Optional;
import org.springframework.stereotype.Service;

import lshh.apirepository.dto.request.ResourceRequestDto;

@Service
public interface ResourceRequestService {

    Optional<Object> getByPath(String path) throws Exception;
    Optional<Object> get(ResourceRequestDto dto) throws Exception;
}
