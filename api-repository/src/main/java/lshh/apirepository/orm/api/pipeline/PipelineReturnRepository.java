package lshh.apirepository.orm.api.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PipelineReturnRepository extends JpaRepository<PipelineReturn, Integer>{
    List<PipelineReturn> findByPipelineId(int pipelineId);
}
