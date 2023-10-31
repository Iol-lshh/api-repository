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
@Table(name = "api_pipeline_step_info")
@Entity
public class PipelineStepInfo  extends RegistedInfo{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;

    @Column(name = "order_number")
    Integer orderNumber;

    @Column(name = "process_type")
    String processType;

    @Column(name = "resourcer_type")
    String resourcerType;

    @Column(name = "pipeline_id")
    Integer pipelineId;

    @Column(name = "query_id")
    Integer queryId;
}
