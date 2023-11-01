package lshh.apirepository.service.api.pipeline;

import java.util.List;

import lshh.apirepository.dto.api.PipelineReturnDto;
import lshh.apirepository.service.ServiceTemplate;

public interface PipelineReturnService extends ServiceTemplate{
    Status save(PipelineReturnDto dto);

    List<PipelineReturnDto> findAll();

    List<PipelineReturnDto> findList(int pipelineId);
    
}
