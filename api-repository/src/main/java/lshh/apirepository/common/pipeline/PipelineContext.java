package lshh.apirepository.common.pipeline;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.service.ServiceTemplate.Status;

import java.util.List;

public interface PipelineContext {

    default Status process(){
        return act(ProcessType.Process);
    }
    default Status rollback(){
        return act(ProcessType.Rollback);
    }
    Status act(ProcessType processType);

    PipelineContext arguments(List<QueryArgumentDto<Object>> arguments);
    List<Object> returnList();

    List<PipelineStep> processList();
    PipelineContext processList(List<PipelineStep> list);
    PipelineContext addProcessStep(PipelineStep step);

    List<PipelineStep> rollbackList();
    PipelineContext rollbackList(List<PipelineStep> list);
    PipelineContext addRollbackStep(PipelineStep step);

    PipelineContext clear();
    PipelineContext clearArgumentPool();
    PipelineContext clearProcessList();
    PipelineContext clearRollbackList();
}
