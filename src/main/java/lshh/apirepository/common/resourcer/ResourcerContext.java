package lshh.apirepository.common.resourcer;

public interface ResourcerContext{
    public enum Type{DB, API}
    int id();
    Type type();
    String name();
    long started();
}
