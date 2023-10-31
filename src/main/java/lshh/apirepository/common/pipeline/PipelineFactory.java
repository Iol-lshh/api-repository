package lshh.apirepository.common.pipeline;

import java.util.List;

import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.request.QueryArgumentDto;

public interface PipelineFactory {

    PipelineContext createContext(int pipelineId, List<QueryArgumentDto<Object>> arguments) throws Exception;
    PipelineStep createStep(PipelineStepDto dto) throws Exception;
}
