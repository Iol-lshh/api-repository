package lshh.apirepository.common.pipeline;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.service.ServiceTemplate.Status;

import java.util.List;
import java.util.Map;

public interface PipelineContext {

    default Status process(){
        return act(ProcessType.Process);
    }
    default Status rollback(){
        return act(ProcessType.Rollback);
    }
    Status act(ProcessType processType);

    Map<String,Object> result();

    List<String> returnList();
    PipelineContext returnList(List<String> resuList);

    PipelineContext arguments(Map<String, Object> args);
    PipelineContext arguments(List<QueryArgumentDto<Object>> arguments);

    List<PipelineStep> processList();
    PipelineContext addProcessStep(PipelineStep step);
    PipelineContext processList(List<PipelineStep> list);

    List<PipelineStep> rollbackList();
    PipelineContext addRollbackStep(PipelineStep step);
    PipelineContext rollbackList(List<PipelineStep> list);

    PipelineContext clear();
    PipelineContext clearArgumentPool();
    PipelineContext clearProcessList();
    PipelineContext clearRollbackList();
}
