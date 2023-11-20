package lshh.apirepository.dto.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class PipelineDto {

    @JsonProperty
    Integer id;

    @JsonProperty
    String name;
    @JsonProperty
    String description;

    LocalDateTime created;
    @JsonProperty
    LocalDateTime deleted;
    @JsonProperty
    boolean isEnabled;
}
