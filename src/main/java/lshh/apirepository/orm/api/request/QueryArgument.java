package lshh.apirepository.orm.api.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lshh.apirepository.orm.RegistedInfo;

@Entity
public class QueryArgument extends RegistedInfo {
    @Id
    int id;

    String queryPrameterId;
    String requestHistoryId;
    String contents;
}
