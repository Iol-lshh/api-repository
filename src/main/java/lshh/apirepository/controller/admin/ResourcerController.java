package lshh.apirepository.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@RequestMapping("/resourcer")
@RestController
public class ResourcerController {

    @Autowired
    ResourcerService resourcerService;


    @GetMapping("/test")
    public Status test(){
        return Status.OK;
    }

    @PostMapping("/new")
    public Status create(ResourcerDto dto){
        resourcerService.save(dto);
        return Status.OK;
    }

    @GetMapping("/list/{pageSize}/{pageNo}")
    public List<ResourcerDto> findList(@RequestParam int pageSize, @RequestParam int pageNo){
        return resourcerService.findList(pageSize, pageNo);
    }

    @GetMapping("/{resourcerId}")
    public ResourcerDto find(int resourcerId){
        return resourcerService.find(resourcerId)
            .orElseGet(null);
    }
}
