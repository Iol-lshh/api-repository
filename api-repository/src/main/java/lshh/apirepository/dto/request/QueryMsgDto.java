package lshh.apirepository.dto.request;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class QueryMsgDto {
    String query;
    List<QueryArgumentDto<Object>> arguments;    
}
