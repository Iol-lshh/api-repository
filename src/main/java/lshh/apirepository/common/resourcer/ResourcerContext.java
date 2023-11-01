package lshh.apirepository.common.resourcer;

import java.util.Map;
import java.util.List;

import lshh.apirepository.dto.request.QueryMsgDto;

public interface ResourcerContext{
    public enum Type{DB, API}
    int id();
    Type type();
    String name();
    long started();

    List<Map<String, Object>> getResource(QueryMsgDto dto) throws Exception;
    <T> List<T> getResource(QueryMsgDto dto, Class<T> requiredType) throws Exception;
}
