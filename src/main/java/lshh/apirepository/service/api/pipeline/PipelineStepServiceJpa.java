package lshh.apirepository.service.api.pipeline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.common.pipeline.PipelineStep.ResourcerType;
import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.api.PipelineStepViewDto;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.orm.api.pipeline.PipelineStepInfo;
import lshh.apirepository.orm.api.pipeline.PipelineStepInfoRepository;
import lshh.apirepository.service.api.query.QueryService;

@Service
public class PipelineStepServiceJpa implements PipelineStepService {

    @Autowired
    PipelineStepInfoRepository pipelineStepInfoRepository;

    @Autowired
    QueryService queryService;

    public PipelineStepDto toDto(PipelineStepInfo entity) {
        return new PipelineStepDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .processType(ProcessType.valueOf(entity.processType()))
            .resourcerType(ResourcerType.valueOf(entity.resourcerType()))
            .pipelineId(entity.pipelineId())
            .queryId(entity.queryId())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());
    }

    public PipelineStepInfo toEntity(PipelineStepDto dto){
        PipelineStepInfo result = new PipelineStepInfo()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .processType(dto.processType().name())
            .resourcerType(dto.resourcerType().name())
            .pipelineId(dto.pipelineId())
            .queryId(dto.queryId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Status save(PipelineStepDto dto) throws Exception {
        
        PipelineStepInfo pipelineStepInfo;
        if(dto.id() == null){
            pipelineStepInfo = new PipelineStepInfo();
            pipelineStepInfo.setCreated(LocalDateTime.now());
        }else {
            pipelineStepInfo = pipelineStepInfoRepository.findById(dto.id())
                .orElseGet(()->{
                    PipelineStepInfo _pipelineStepInfo = new PipelineStepInfo();
                    _pipelineStepInfo.setCreated(LocalDateTime.now());
                    return _pipelineStepInfo;
                });
        }
        pipelineStepInfo.name(dto.name()!=null?dto.name():pipelineStepInfo.name())
            .processType(dto.processType()!=null?dto.processType().name():pipelineStepInfo.processType())
            .resourcerType(dto.resourcerType()!=null?dto.resourcerType().name():pipelineStepInfo.resourcerType())
            .description(dto.description()!=null?dto.description():pipelineStepInfo.description())
            .pipelineId(dto.pipelineId()!=null?dto.pipelineId():pipelineStepInfo.pipelineId())
            .queryId(dto.queryId()!=null?dto.queryId():pipelineStepInfo.queryId());
        pipelineStepInfo.setEnabled(dto.isEnabled());
        if(dto.deleted()!=null){
            pipelineStepInfo.setDeleted(dto.deleted());
        }

        pipelineStepInfoRepository.save(pipelineStepInfo);
        return Status.OK;
    }

    @Override
    public List<PipelineStepDto> findList(int pipelineId, ProcessType processType) {
        return pipelineStepInfoRepository.findByPipelineIdAndProcessType(pipelineId, processType.name())
            .stream().map(this::toDto).toList();
    }

    @Override
    public List<PipelineStepDto> findList(int pipelineId) {
        return pipelineStepInfoRepository.findByPipelineId(pipelineId)
            .stream().map(this::toDto).toList();
    }

    @Override
    public Optional<PipelineStepDto> find(int id) {
        return pipelineStepInfoRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<PipelineStepDto> findAll() {
        return pipelineStepInfoRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public PipelineStepViewDto findView(int pipelineStepId) throws Exception {
        
        PipelineStepDto pipelineStepDto = pipelineStepInfoRepository.findById(pipelineStepId).map(this::toDto).orElse(null);
        QueryDto queryDto = null;
        if(pipelineStepDto != null && pipelineStepDto.queryId() != null){
            queryDto = queryService.find(pipelineStepDto.queryId()).orElse(null);
        }
        return new PipelineStepViewDto()
            .pipelineStep(pipelineStepDto)
            .query(queryDto);
    }
    
}
