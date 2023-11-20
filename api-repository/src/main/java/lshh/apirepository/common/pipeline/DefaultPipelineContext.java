package lshh.apirepository.common.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.query.QueryService;

public class DefaultPipelineContext implements PipelineContext {

    List<PipelineStep> processList;
    List<PipelineStep> rollbackList;

    Map<String, Object> argumentPool;
    List<Object> returnList;

    QueryService queryService;

    public DefaultPipelineContext(QueryService queryService){
        this.queryService = queryService;
        this.returnList = new ArrayList<>();
        this.argumentPool = new HashMap<>();
    }

    public Status act(ProcessType processType) {
        List<PipelineStep> actList;
        switch (processType) {
            case Rollback:
                actList = this.rollbackList;
                break;
            case Process:
            default:
                actList = this.processList;
                break;
        }
        try{
            for(PipelineStep  step: actList){
                // 받은 결과값을 다시 요청
                // 1. pipelineStep이 가진 inputParameters와 같은 이름의 argument들을 argumentPool에서 가져온다
                List<QueryArgumentDto<Object>> args = step.inputParameters().stream().map(e->{
                    // alias 우선
                    String findAlias = e.alias() !=null ? e.alias() : e.name();
                    return new QueryArgumentDto<>()
                        .parameterId(e.id())
                        .name(findAlias)
                        .value(argumentPool.get(findAlias));
                }).toList();
                
                if(step.arguments(args).act() == Status.OK){
                    // 2. 결과 넣는다.
                    System.out.println("리소서 요청 결과: "+step.result());
                    returnList.add(step.result());
                    
                    // 3. pipelineStep이 가진 outputParameters와 같은 이름의 argument를 argument pool map 에 넣는다. (덮어쓰기)
                    List<String> outputNames = step.outputParameters()
                        .stream()
                        .map(e -> e.alias() !=null ? e.alias() : e.name())
                        .toList();
                    
                    step.result().forEach(
                        e0 -> e0.entrySet()
                            .stream()
                            .filter(e1 -> outputNames.contains(e1.getKey()))
                            .forEach(e2 -> argumentPool.put(e2.getKey(), e2.getValue()))
                    );
                }else{
                    throw new PipelineProcessFailException("FAIL: "+ step.query().id());
                }
            }
        }catch(Exception err){
            System.out.println(err);
            return Status.FAIL;
        }
        return Status.OK;
    }

    @Override
    public PipelineContext arguments(List<QueryArgumentDto<Object>> arguments) {
        arguments.stream().forEach(e->this.argumentPool.put(e.name(), e.value()));
        return this;
    }

    @Override
    public List<PipelineStep> processList() {
        return this.processList;
    }
    @Override
    public PipelineContext processList(List<PipelineStep> list) {
        this.processList = list;
        return this;
    }

    @Override
    public List<PipelineStep> rollbackList() {
        return this.rollbackList;
    }
    @Override
    public PipelineContext rollbackList(List<PipelineStep> list) {
        this.rollbackList = list;
        return this;
    }

    @Override
    public PipelineContext clear() {
        clearArgumentPool();
        clearProcessList();
        clearRollbackList();
        return this;
    }

    @Override
    public PipelineContext clearArgumentPool(){
        this.argumentPool.clear();
        return this;
    }

    @Override
    public PipelineContext clearProcessList() {
        this.processList.clear();
        return this;
    }

    @Override
    public PipelineContext clearRollbackList() {
       this.rollbackList.clear();
       return this;
    }

    @Override
    public PipelineContext addProcessStep(PipelineStep step) {
        this.processList.add(step);
        return this;
    }

    @Override
    public PipelineContext addRollbackStep(PipelineStep step) {
        this.rollbackList.add(step);
        return this;
    }

    @Override
    public List<Object> returnList(){
        return this.returnList;
    }
    
}
