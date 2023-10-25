package lshh.apirepository.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;
import lshh.apirepository.dto.api.QueryParameterDto;

@Accessors(chain=true,fluent=true)
@Data
public class QueryArgumentDto<T> {
    @JsonProperty
    QueryParameterDto parameter;
    @JsonProperty
    T value;
}
