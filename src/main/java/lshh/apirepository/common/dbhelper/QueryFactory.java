package lshh.apirepository.common.dbhelper;

public interface QueryFactory {
    <T extends QueryStatement> T createStatement();
}
