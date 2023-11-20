package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.PipelineStepParameterDto;
import lshh.apirepository.service.ServiceTemplate.Status;

import lshh.apirepository.service.api.pipeline.PipelineStepParameterService;

@RestController
@RequestMapping("/pipeline-step-parameter")
public class PipelineStepParameterController {
    @Autowired
    PipelineStepParameterService pipelineStepParameterService;

    @PostMapping("")
    public Status save(@RequestBody PipelineStepParameterDto dto) throws Exception{
        return pipelineStepParameterService.save(dto);
    } 
}
