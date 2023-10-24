package lshh.apirepository.common.resourcer;

public interface ResourcerContext{
    public enum Type{DB, API}
    int getId();
    Type getType();
    long getStarted();
}
