package lshh.apirepository.dto.api;

import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;
import lshh.apirepository.common.resourcer.ResourcerContext.Type;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class ResourcerContextDto {
    @JsonProperty
    int id;
    @JsonProperty
    Type type;
    @JsonProperty
    String name;
    @JsonProperty
    LocalDateTime started;

    public ResourcerContextDto started(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        this.started = localDateTime;
        return this;
    }
}
