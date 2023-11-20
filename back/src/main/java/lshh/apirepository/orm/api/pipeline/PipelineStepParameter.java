package lshh.apirepository.orm.api.pipeline;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;

@Entity
@Table(name = "api_pipeline_step_parameter")
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class PipelineStepParameter extends RegistedInfo{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;
    String alias;
    String iotype;

    @Column(name="step_id")
    Integer pipelineStepId;
}
