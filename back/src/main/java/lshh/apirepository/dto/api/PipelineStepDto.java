package lshh.apirepository.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;
import lshh.apirepository.common.pipeline.PipelineStep.ProcessType;
import lshh.apirepository.common.pipeline.PipelineStep.ResourcerType;

import java.time.LocalDateTime;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class PipelineStepDto {
    
    @JsonProperty
    Integer id;

    @JsonProperty
    ProcessType processType;
    @JsonProperty
    ResourcerType resourcerType;

    @JsonProperty
    String name;
    @JsonProperty
    String description;

    @JsonProperty
    Integer pipelineId;
    @JsonProperty
    Integer queryId;

    LocalDateTime created;
    @JsonProperty
    LocalDateTime deleted;
    @JsonProperty
    boolean isEnabled;
}
