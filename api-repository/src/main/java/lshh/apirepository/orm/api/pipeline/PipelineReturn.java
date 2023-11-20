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

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Table(name = "api_pipeline_return")
@Entity
public class PipelineReturn extends RegistedInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;

    @Column(name="pipeline_id")
    Integer pipelineId;
}