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
public class ResourcerDto {
    @JsonProperty
    Integer id;

    @JsonProperty
    String name;
    @JsonProperty
    String path;
    @JsonProperty
    String description;

    @JsonProperty
    String accessName;
    @JsonProperty
    String key;

    @JsonProperty
    String driver;
    @JsonProperty
    String driverClassName;
    
    LocalDateTime created;
    LocalDateTime deleted;
    boolean isEnabled;
}
