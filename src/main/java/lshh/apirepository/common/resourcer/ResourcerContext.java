package lshh.apirepository.common.resourcer;

import java.util.Map;
import java.util.List;

import lshh.apirepository.dto.request.QueryRequestDto;

public interface ResourcerContext{
    public enum Type{DB, API}
    int id();
    Type type();
    String name();
    long started();

    List<Map<String, Object>> getResource(QueryRequestDto dto) throws Exception;
    <T> List<T> getResource(QueryRequestDto dto, Class<T> requiredType) throws Exception;
}
