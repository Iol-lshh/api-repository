package lshh.apirepository.controller.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.request.ResourceRequestDto;
import lshh.apirepository.service.api.request.ResourceRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ResourceRequestController {

    @Autowired
    ResourceRequestService resourceRequestService;

    @GetMapping("/{path}")
    public Object routing(@PathVariable("path") String path) throws Exception {
        return resourceRequestService.getByPath(path).orElse(null);
    }
    
    @PostMapping("")
    public Object routing(@RequestBody ResourceRequestDto dto) throws Exception{
        return resourceRequestService.get(dto).orElse(null);
    }


}
