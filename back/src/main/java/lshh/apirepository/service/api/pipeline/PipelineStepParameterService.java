package lshh.apirepository.service.api.pipeline;

import java.util.List;

import lshh.apirepository.dto.api.PipelineStepParameterDto;
import lshh.apirepository.service.ServiceTemplate;

public interface PipelineStepParameterService extends ServiceTemplate{

    public enum Iotype{INPUT, OUTPUT}

    Status save(PipelineStepParameterDto dto) throws Exception;

    List<PipelineStepParameterDto> findList(int pipelineStepId, String name);
}
