package lshh.apirepository.common.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.query.QueryService;

public class DefaultPipelineContext implements PipelineContext {


    List<PipelineStep> processList;
    List<PipelineStep> rollbackList;

    Map<String, Object> argumentPool;
    List<String> returnList;

    QueryService queryService;

    public DefaultPipelineContext(QueryService queryService){
        this.queryService = queryService;
        this.returnList = new ArrayList<String>();
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
            for(PipelineStep context: actList){
                // 받은 결과값을 다시 요청
                // 1. pipelineStep이 가진 query의 parameter와 같은 이름의 argument를 pool에서 가져온다
                List<QueryParameterDto> params = queryService
                    .findView(context.query().id())
                    .queryParameters();

                List<QueryArgumentDto<Object>> args = params.stream().map(e->{
                    return new QueryArgumentDto<>()
                        .queryParameterId(e.id())
                        .queryParameterName(e.name())
                        .value(argumentPool.get(e.name()));
                }).toList();
                
                if(context.arguments(args).act() == Status.OK){
                    System.out.println("리소서 요청 결과: "+context.result());
                    // 2. 결과값을 argument pool map 에 넣는다. (덮어쓰기)
                    context.result().stream().forEach(e->argumentPool.putAll(e));
                    
                }else{
                    throw new PipelineProcessFailException("fail: "+ context.query().id());
                }
            }
        }catch(Exception e){
            return Status.FAIL;
        }
        return Status.OK;
    }

    @Override
    public PipelineContext arguments(Map<String, Object> args){
        this.argumentPool.putAll(args);
        return this;
    }

    @Override
    public PipelineContext arguments(List<QueryArgumentDto<Object>> arguments) {
        arguments.stream().forEach(e->this.argumentPool.put(e.queryParameterName(), e.value()));
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
    public PipelineContext returnList(List<String> resuList){
        this.returnList = resuList;
        return this;
    }

    @Override
    public List<String> returnList(){
        return this.returnList;
    }

    @Override
    public Map<String,Object> result(){

        Map<String, Object> result = new HashMap<>();
        this.returnList.forEach(s -> result.put(s, this.argumentPool.get(s)));
        
        return result;
    }
    
}
