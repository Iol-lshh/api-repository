package lshh.apirepository.common.resourcer;

public interface ResourcerFactory{
    ResourcerContext initResourcer(int id, Object args) throws Exception;

    boolean existsResourcer(int id);
}
