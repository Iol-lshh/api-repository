package lshh.apirepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lshh.apirepository.dto.api.ResourcerDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.resource.ResourcerService;

@Controller
public class ResourcerController {

    @Autowired
    ResourcerService resourcerService;

    @PostMapping()
    public Status create(ResourcerDto resourcerDto){

        resourcerService.save(resourcerDto);
        return Status.OK;
    }
}
