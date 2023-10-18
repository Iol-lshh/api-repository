package lshh.apirepository.dto.api;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class QueryDto {
    int id;

    String name;
    String contents;
    String description;

    String resourcerId;

    List<QueryParameterDto> queryParameterList;
}
