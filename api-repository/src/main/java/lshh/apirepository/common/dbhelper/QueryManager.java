package lshh.apirepository.common.dbhelper;

public interface QueryManager {
    QueryFactory factory();
    QueryStatement createStatement();
}
