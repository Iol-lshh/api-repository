package lshh.apirepository.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;
import lshh.apirepository.dto.api.QueryParameterDto;

@Accessors(chain=true,fluent=true)
@Data
public class QueryArgumentDto<T> {
    QueryParameterDto parameter;
    T value;
}
