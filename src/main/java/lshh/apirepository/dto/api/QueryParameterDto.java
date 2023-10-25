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
public class QueryParameterDto {
    @JsonProperty
    Integer id;
    
    @JsonProperty
    String name;
    @JsonProperty
    String description;
    @JsonProperty
    String ioType;
    @JsonProperty
    boolean isOptional;

    @JsonProperty
    Integer queryId;

    LocalDateTime created;
    LocalDateTime deleted;
    boolean isEnabled;
}
