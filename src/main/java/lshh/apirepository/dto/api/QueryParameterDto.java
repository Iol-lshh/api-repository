package lshh.apirepository.dto.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class QueryParameterDto {
    int id;
    String name;
    String description;
    String type;
    boolean isOptional;
}
