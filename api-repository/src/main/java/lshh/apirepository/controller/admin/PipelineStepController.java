package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.service.api.pipeline.PipelineStepService;
import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.api.PipelineStepViewDto;
import lshh.apirepository.service.ServiceTemplate.Status;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pipeline-step")
public class PipelineStepController {
    @Autowired
    PipelineStepService pipelineStepService;

    @GetMapping("/list/{pipelineId}/{processType}")
    public List<PipelineStepDto> findList(@PathVariable int pipelineId, @PathVariable String processType){
        return pipelineStepService.findList(pipelineId, ProcessType.valueOf(processType));
    }

    @GetMapping("/list/{pipelineId}")
    public List<PipelineStepDto> findList(@PathVariable int pipelineId){
        return pipelineStepService.findList(pipelineId);
    }

    @GetMapping("/{pipelineStepId}")
    public PipelineStepDto find(@PathVariable int pipelineStepId){
        return pipelineStepService.find(pipelineStepId).orElse(null);
    }

    @PostMapping("")
    public Status postMethodName(@RequestBody PipelineStepDto dto) throws Exception {
        return pipelineStepService.save(dto);
    }
    
    @GetMapping("/all")
    public List<PipelineStepDto> findAll(){
        return pipelineStepService.findAll();
    }

    @GetMapping("/view/{pipelineStepId}")
    public PipelineStepViewDto findView(@PathVariable int pipelineStepId) throws Exception{
        return pipelineStepService.findView(pipelineStepId);
    }
}
