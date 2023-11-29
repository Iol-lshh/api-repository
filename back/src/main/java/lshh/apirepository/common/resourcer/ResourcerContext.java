package lshh.apirepository.common.resourcer;

import java.util.List;
import java.util.Map;

import lshh.apirepository.dto.request.QueryMsgDto;

public interface ResourcerContext{
    public enum Type{DB, API}
    int id();
    Type type();
    String name();
    long started();
    List<Map<String, Object>> getResource(QueryMsgDto requestDto) throws Exception;
}
