package lshh.apirepository.service.api.pipeline;

import lshh.apirepository.service.ServiceTemplate;
import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.api.PipelineStepViewDto;

import java.util.List;
import java.util.Optional;

public interface PipelineStepService extends ServiceTemplate{

    Status save(PipelineStepDto dto) throws Exception;

    List<PipelineStepDto> findList(int pipelineId, ProcessType processType);
    Optional<PipelineStepDto> find(int id);

    List<PipelineStepDto> findAll();

    List<PipelineStepDto> findList(int pipelineId);

    PipelineStepViewDto findView(int pipelineStepId) throws Exception;
}
