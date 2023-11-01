package lshh.apirepository.service.api.pipeline;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lshh.apirepository.dto.api.PipelineStepParameterDto;
import lshh.apirepository.orm.api.pipeline.PipelineStepParameter;
import lshh.apirepository.orm.api.pipeline.PipelineStepParameterRepository;

@Service
public class PipelineStepParameterServiceJpa implements PipelineStepParameterService{

    @Autowired
    PipelineStepParameterRepository pipelineStepParameterRepository;

    public PipelineStepParameterDto toDto(PipelineStepParameter entity){
        return new PipelineStepParameterDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .alias(entity.alias())
            .iotype(entity.iotype())
            .pipelineStepId(entity.pipelineStepId())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());

    }

    public PipelineStepParameter toEntity(PipelineStepParameterDto dto){
        PipelineStepParameter result = new PipelineStepParameter();
        result.id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .alias(dto.alias())
            .iotype(dto.iotype())
            .pipelineStepId(dto.pipelineStepId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Status save(PipelineStepParameterDto dto) throws Exception {

        PipelineStepParameter entity;
        if(dto.id()==null){
            entity = new PipelineStepParameter();
            entity.setCreated(LocalDateTime.now());
        }else{
            entity = pipelineStepParameterRepository.findById(dto.id())
                .orElseGet(()->{
                    PipelineStepParameter _entity = new PipelineStepParameter();
                    _entity.setCreated(LocalDateTime.now());
                    return _entity;
                });
        }

        entity
            .name(dto.name()!=null?dto.name():entity.name())
            .description(dto.description()!=null?dto.description():entity.description())
            .alias(dto.alias()!=null?dto.alias():entity.alias())
            .pipelineStepId(dto.pipelineStepId()!=null?dto.pipelineStepId():entity.pipelineStepId())
            .iotype(dto.iotype()!=null?dto.iotype():entity.iotype())
            .setEnabled(dto.isEnabled());
        if(dto.deleted()!=null){
            entity.setDeleted(dto.deleted());
        }

        pipelineStepParameterRepository.save(entity);
        return Status.OK;
    }

    @Override
    public List<PipelineStepParameterDto> findList(int pipelineStepId, String iotype) {
        return pipelineStepParameterRepository
            .findByPipelineStepIdAndIotype(pipelineStepId, iotype)
            .stream()
            .map(this::toDto)
            .toList();
    }
    
}
