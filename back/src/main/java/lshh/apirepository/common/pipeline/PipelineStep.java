package lshh.apirepository.common.pipeline;

import lshh.apirepository.dto.api.PipelineStepParameterDto;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.service.ServiceTemplate.Status;

import java.util.List;
import java.util.Map;

public interface PipelineStep {

    public enum ProcessType{
        Process, Rollback
    }
    
    public enum ResourcerType{
        DB, API
    }

    PipelineStep query(QueryDto queryDto);
    QueryDto query();
    List<PipelineStepParameterDto> inputParameters();
    PipelineStep inputParameters(List<PipelineStepParameterDto> inputs);
    List<PipelineStepParameterDto> outputParameters();
    PipelineStep outputParameters(List<PipelineStepParameterDto> outputs);
    PipelineStep arguments(List<QueryArgumentDto<Object>> dtos);
    List<QueryArgumentDto<Object>> arguments();

    Status act() throws PipelineProcessFailException;
    Status status();
    List<Map <String, Object>> result();
}
