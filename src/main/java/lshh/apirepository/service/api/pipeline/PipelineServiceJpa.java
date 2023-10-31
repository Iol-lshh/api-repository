package lshh.apirepository.service.api.pipeline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.dto.api.PipelineDto;
import lshh.apirepository.dto.api.PipelineReturnDto;
import lshh.apirepository.dto.api.PipelineStepDto;
import lshh.apirepository.dto.api.PipelineViewDto;
import lshh.apirepository.orm.api.pipeline.PipelineInfo;
import lshh.apirepository.orm.api.pipeline.PipelineInfoRepository;

@Service
public class PipelineServiceJpa implements PipelineService {

    @Autowired
    PipelineInfoRepository pipelineInfoRepository;
    @Autowired
    PipelineStepService pipelineStepService;
    @Autowired
    PipelineReturnService pipelineReturnService;

    public PipelineDto toDto(PipelineInfo entity) {
        return new PipelineDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());
    }

    public PipelineInfo toEntity(PipelineDto dto){
        PipelineInfo result = new PipelineInfo()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Status save(PipelineDto dto) throws Exception {
        PipelineInfo pipelineInfo;
        if(dto.id() == null){
            pipelineInfo = new PipelineInfo();
            pipelineInfo.setCreated(LocalDateTime.now());
        }else{
            pipelineInfo = pipelineInfoRepository.findById(dto.id())
                .orElseGet(()->{
                    PipelineInfo _pipelineInfo = new PipelineInfo();
                    _pipelineInfo.setCreated(LocalDateTime.now());
                    return _pipelineInfo;
                });
        }
        pipelineInfo
            .name(dto.name()!=null?dto.name():pipelineInfo.name())
            .description(dto.description()!=null?dto.description():pipelineInfo.description())
            .setEnabled(dto.isEnabled());
        if(dto.deleted()!=null){
            pipelineInfo.setDeleted(dto.deleted());
        }
        pipelineInfoRepository.save(pipelineInfo);
        return Status.OK;
    }

    @Override
    public List<PipelineDto> findAll() {
        return pipelineInfoRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<PipelineDto> findList(int pageSize, int pageNo) {
        return pipelineInfoRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNo))
            .stream().map(this::toDto).toList();
    }

    @Override
    public Optional<PipelineDto> find(int id) {
        return pipelineInfoRepository.findById(id).map(e->toDto(e));
    }

    @Override
    public PipelineViewDto findView(Integer id) {

        PipelineDto pipeline = find(id).orElse(null);
        List<PipelineStepDto> processSteps = pipelineStepService.findList(id, ProcessType.Process);
        List<PipelineStepDto> rollbackSteps = pipelineStepService.findList(id, ProcessType.Rollback);
        List<PipelineReturnDto> returnList = pipelineReturnService.findList(id);
        return new PipelineViewDto()
            .pipeline(pipeline)
            .processSteps(processSteps)
            .rollbackSteps(rollbackSteps)
            .returnList(returnList);
    }
    
}
