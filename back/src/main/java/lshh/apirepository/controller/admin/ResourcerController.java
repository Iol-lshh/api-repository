package lshh.apirepository.controller.admin;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.common.resourcer.JdbcResourcerManager;
import lshh.apirepository.dto.api.ResourcerContextDto;
import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@RequestMapping("/resourcer")
@RestController
public class ResourcerController {

    @Autowired
    ResourcerService resourcerService;

    @Autowired
    JdbcResourcerManager resourcerManager;

    @GetMapping("/test")
    public Status test(){
        return Status.OK;
    }

    @PostMapping("")
    public Status save(@RequestBody ResourcerDto dto){
        resourcerService.save(dto);
        return Status.OK;
    }

    @GetMapping("/list/{pageSize}/{pageNo}")
    public List<ResourcerDto> findList(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo){
        return resourcerService.findList(pageSize, pageNo);
    }

    @GetMapping("/list/all")
    public List<ResourcerDto> findAll(){
        return resourcerService.findAll();
    }

    @GetMapping("/{resourcerId}")
    public ResourcerDto find(@PathVariable int resourcerId){
        return resourcerService.find(resourcerId)
            .orElse(null);
    }

    @GetMapping("/cachedList")
    public List<ResourcerContextDto> getCachedList(){
        return resourcerManager.getContextList();
    }
}
