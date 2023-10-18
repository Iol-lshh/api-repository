package lshh.apirepository.common.resourcer;

public interface ResourcerContext{
    int getId();
    String getType();
    long getStarted();

    Object getResource(Object... args);
    <T> T getResource(Class<T> requiredType);
    <T> T getResource(Class<T> requiredType, Object... args);
}
