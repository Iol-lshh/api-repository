package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.PipelineReturnDto;
import lshh.apirepository.service.api.pipeline.PipelineReturnService;

import lshh.apirepository.service.ServiceTemplate.Status;

import java.util.List;

@RestController
@RequestMapping("/pipeline-return")
public class PipelineReturnController {
    @Autowired
    PipelineReturnService pipelineReturnService;

    @PostMapping
    public Status save(@RequestBody PipelineReturnDto dto){
        return pipelineReturnService.save(dto);
    }

    @GetMapping("/all")
    public List<PipelineReturnDto> findAll(){
        return pipelineReturnService.findAll();
    }
}
