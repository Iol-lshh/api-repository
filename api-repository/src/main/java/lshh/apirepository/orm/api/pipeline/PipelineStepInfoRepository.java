package lshh.apirepository.orm.api.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PipelineStepInfoRepository extends JpaRepository<PipelineStepInfo, Integer>{
    List<PipelineStepInfo> findByPipelineId(int pipelineId);

    List<PipelineStepInfo> findByPipelineIdAndProcessType(int pipelineId, String name);
}
