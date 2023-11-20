package lshh.apirepository.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class PipelineViewDto {
    @JsonProperty
    PipelineDto pipeline;
    @JsonProperty
    List<PipelineStepDto> processSteps;
    @JsonProperty
    List<PipelineStepDto> rollbackSteps;
}
