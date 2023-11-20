package lshh.apirepository.orm.api.pipeline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelineStepParameterRepository extends JpaRepository<PipelineStepParameter, Integer>{

    List<PipelineStepParameter> findByPipelineStepId(int pipelineStepId);

    List<PipelineStepParameter> findByPipelineStepIdAndIotype(int pipelineStepId, String iotype);
    
}
