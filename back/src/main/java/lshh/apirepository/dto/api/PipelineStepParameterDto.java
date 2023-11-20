package lshh.apirepository.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class PipelineStepParameterDto {
    @JsonProperty
    Integer id;

    @JsonProperty 
    String name;
    @JsonProperty
    String alias;   // 우선 조회 > 없으면 name 조회
    @JsonProperty
    String description;

    @JsonProperty
    String iotype;

    @JsonProperty
    Integer pipelineStepId;

    LocalDateTime created;
    @JsonProperty
    LocalDateTime deleted;
    @JsonProperty
    boolean isEnabled;
}
