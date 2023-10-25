package lshh.apirepository.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain=true,fluent=true)
@Data
public class QueryArgumentDto<T> {
    @JsonProperty
    int queryParameterId;
    @JsonProperty
    String queryParameterName;
    @JsonProperty
    T value;
}
