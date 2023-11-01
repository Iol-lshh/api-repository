package lshh.apirepository.service.api.pipeline;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lshh.apirepository.dto.api.PipelineReturnDto;
import lshh.apirepository.orm.api.pipeline.PipelineReturn;
import lshh.apirepository.orm.api.pipeline.PipelineReturnRepository;

@Service
public class PipelineReturnServiceJpa implements PipelineReturnService {

    @Autowired
    PipelineReturnRepository pipelineReturnRepository;

    public PipelineReturnDto toDto(PipelineReturn entity){
        return new PipelineReturnDto()
            .id(entity.id())
            .name(entity.name())
            .description(entity.description())
            .piplineId(entity.pipelineId())
            .created(entity.getCreated())
            .deleted(entity.getDeleted())
            .isEnabled(entity.isEnabled());
    }

    public PipelineReturn toEntity(PipelineReturnDto dto){
        PipelineReturn result = new PipelineReturn()
            .id(dto.id())
            .name(dto.name())
            .description(dto.description())
            .pipelineId(dto.piplineId());
        result.setCreated(dto.created());
        result.setDeleted(dto.deleted());
        result.setEnabled(dto.isEnabled());
        return result;
    }

    @Override
    public Status save(PipelineReturnDto dto) {
        PipelineReturn pipelineReturn;
        if(dto.id() == null){
            pipelineReturn = new PipelineReturn();
            pipelineReturn.setCreated(LocalDateTime.now());
        }else{
            pipelineReturn = pipelineReturnRepository.findById(dto.id())
                .orElseGet(()->{
                    PipelineReturn _pipelineReturn = new PipelineReturn();
                    _pipelineReturn.setCreated(LocalDateTime.now());
                    return _pipelineReturn;
                });
        }
        
        pipelineReturn
            .name(dto.name()!=null?dto.name():pipelineReturn.name())
            .description(dto.description()!=null?dto.description():pipelineReturn.description())
            .pipelineId(dto.piplineId()!=null?dto.piplineId():pipelineReturn.pipelineId())
            .setEnabled(dto.isEnabled());
        if(dto.deleted()!=null){
            pipelineReturn.setDeleted(dto.deleted());
        }
        pipelineReturnRepository.save(pipelineReturn);
        return Status.OK;
    }

    @Override
    public List<PipelineReturnDto> findAll() {
        return pipelineReturnRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<PipelineReturnDto> findList(int pipelineId) {
        return pipelineReturnRepository.findByPipelineId(pipelineId).stream().map(this::toDto).toList();
    }
    
}
