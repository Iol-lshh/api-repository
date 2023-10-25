package lshh.apirepository.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class ResourceRequestDto {
    @JsonProperty
    String path;
    @JsonProperty
    List<QueryArgumentDto> aruments;
}
