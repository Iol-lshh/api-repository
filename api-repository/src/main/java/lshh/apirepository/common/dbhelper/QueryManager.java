package lshh.apirepository.common.dbhelper;

public interface QueryManager {
    QueryFactory factory();
    <T extends QueryStatement> T createStatement();
}
