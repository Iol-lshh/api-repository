package lshh.apirepository.orm.api.pipeline;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PipelineStepParameter {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;

    @Column(name="is_writable")
    boolean isWritable; // READ_ONLY, WRITABLE

    @Column(name="step_id")
    Integer pipelineStepId;
}
