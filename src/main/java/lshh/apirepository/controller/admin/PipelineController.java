package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.PipelineDto;
import lshh.apirepository.dto.api.PipelineViewDto;
import lshh.apirepository.service.api.pipeline.PipelineService;
import lshh.apirepository.service.ServiceTemplate.Status;
import java.util.List;


@RestController
@RequestMapping("/pipeline")
public class PipelineController {
    
    @Autowired
    PipelineService pipelineService;

    @PostMapping("")
    public Status save(@RequestBody PipelineDto pipelineDto) throws Exception{
        return pipelineService.save(pipelineDto);
    }

    @GetMapping("/{pipelineId}")
    public PipelineDto find(@PathVariable int piplineId){
        return pipelineService.find(piplineId).orElse(null);
    }

    @GetMapping("/all")
    public List<PipelineDto> findAll(){
        return pipelineService.findAll();
    }

    @GetMapping("/list/{pageSize}/{pageNo}")
    public List<PipelineDto> findList(@PathVariable int pageSize, @PathVariable int pageNo) {
        return pipelineService.findList(pageSize, pageNo);
    }
    
    @GetMapping("/view/{pipelineId}")
    public PipelineViewDto getMethodName(@PathVariable int pipelineId) {
        return pipelineService.findView(pipelineId);
    }
    

}
