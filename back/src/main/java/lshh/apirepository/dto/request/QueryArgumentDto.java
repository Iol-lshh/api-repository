package lshh.apirepository.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain=true,fluent=true)
@Data
public class QueryArgumentDto<T> {
    @JsonProperty
    String name;    //쿼리에 쓰인 이름
    @JsonProperty
    T value;
    @JsonProperty
    Integer parameterId; //실제 데이터 아이디
}
