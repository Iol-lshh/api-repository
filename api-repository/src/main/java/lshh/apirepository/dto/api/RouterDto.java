package lshh.apirepository.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class RouterDto {
    @JsonProperty
    Integer id;

    @JsonProperty
    String name;
    @JsonProperty
    String path;
    @JsonProperty
    String description;
    
    @JsonProperty
    Integer queryId;

    LocalDateTime created;
    LocalDateTime deleted;
    boolean isEnabled;
}
