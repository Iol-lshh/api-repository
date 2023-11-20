package lshh.apirepository.service.api.pipeline;

import lshh.apirepository.dto.api.PipelineDto;
import lshh.apirepository.dto.api.PipelineViewDto;
import lshh.apirepository.service.ServiceTemplate;

import java.util.List;
import java.util.Optional;

public interface PipelineService extends ServiceTemplate{
    Status save(PipelineDto dto) throws Exception;

    List<PipelineDto> findAll();
    List<PipelineDto> findList(int pageSize, int pageNo);

    Optional<PipelineDto> find(int id);

    PipelineViewDto findView(Integer id);
}
