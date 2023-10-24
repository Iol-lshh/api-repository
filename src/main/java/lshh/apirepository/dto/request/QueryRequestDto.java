package lshh.apirepository.dto.request;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class QueryRequestDto {
    String query;
    List<QueryArgumentDto> arguments;    
}
