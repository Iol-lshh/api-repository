package lshh.apirepository.common.pipeline;

import java.util.List;

import lshh.apirepository.dto.request.QueryArgumentDto;

public interface PipelineManager {

    PipelineFactory factory();
    PipelineContext createContext(int pipelineId, List<QueryArgumentDto<Object>> arguments) throws Exception;
    
}
