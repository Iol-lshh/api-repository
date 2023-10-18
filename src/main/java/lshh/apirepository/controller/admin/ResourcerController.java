package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.resourcer.ResourcerService;

@RestController
@RequestMapping("/resourcer")
public class ResourcerController {

    @Autowired
    ResourcerService resourcerService;


    @GetMapping("/test")
    public Status test(){
        return Status.OK;
    }

    @PostMapping("/new")
    public Status create(ResourcerDto resourcerDto){

        resourcerService.save(resourcerDto);
        return Status.OK;
    }
}
