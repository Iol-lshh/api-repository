package lshh.apirepository.common.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.common.pipeline.PipelineStep.ResourcerType;
import lshh.apirepository.common.resourcer.ResourcerContext;
import lshh.apirepository.common.resourcer.ResourcerManager;
import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.orm.api.pipeline.PipelineReturnRepository;
import lshh.apirepository.service.api.pipeline.PipelineStepService;
import lshh.apirepository.service.api.pipeline.PipelineService;
import lshh.apirepository.service.api.query.QueryService;

import java.util.List;
import java.util.ArrayList;

@Component
public class DefaultPipelineManager implements PipelineManager{
    
    PipelineFactory factory;

    @Autowired
    PipelineService pipelineService;
    @Autowired
    PipelineStepService pipelineStepService;
    @Autowired
    PipelineReturnRepository pipelineReturnRepository;
    @Autowired
    QueryService queryService;

    @Autowired
    ResourcerManager resourcerManager;

    public DefaultPipelineManager(){

        this.factory = new DefaultPipelineFactory();

    }

    @Override
    public PipelineFactory factory(){

        return this.factory;
    }

    @Override
    public PipelineContext createContext(int pipelineId, List<QueryArgumentDto<Object>> arguments) throws Exception{

        return this.factory.createContext(pipelineId, arguments);
    }

    // # factory
    public class DefaultPipelineFactory implements PipelineFactory{

        @Override
        public PipelineContext createContext(int pipelineId, List<QueryArgumentDto<Object>> arguments) throws Exception {

            List<PipelineStep> processList = new ArrayList<>();
            List<PipelineStepDto> processDtos = pipelineStepService.findList(pipelineId, ProcessType.Process);
            for (PipelineStepDto dto : processDtos) {
                PipelineStep step = createStep(dto);
                processList.add(step);
            }

            List<PipelineStep> rollbackList = new ArrayList<>();
            List<PipelineStepDto> rollbackDtos = pipelineStepService.findList(pipelineId, ProcessType.Rollback);
            for (PipelineStepDto dto : rollbackDtos) {
                PipelineStep step = createStep(dto);
                rollbackList.add(step);
            }

            List<String> returnList = pipelineReturnRepository.findByPipelineId(pipelineId)
                .stream().map(e->e.name()).toList();

            return new DefaultPipelineContext(queryService)
                .arguments(arguments)
                .processList(processList)
                .rollbackList(rollbackList)
                .returnList(returnList);

        }

        @Override
        public PipelineStep createStep(PipelineStepDto dto) throws Exception{

            PipelineStep resultStep;

            switch(dto.resourcerType()){
                case DB:
                    QueryDto queryDto = queryService.find(dto.queryId())
                        .orElseThrow(()->new Exception("잘못된 쿼리"));

                    ResourcerContext resourcer = resourcerManager.getResourcer(queryDto.resourcerId());
                    resultStep = new DbPipelineStep()
                        .resourcer(resourcer)
                        .resourcerType(ResourcerType.DB)
                        .processType(dto.processType())
                        .query(queryDto);

                    return resultStep;
                case API:
                    // todo
                    return null;
                default: 
                    throw new Exception("지원하지 않는 프로세스");
            }
        }

    }

}
