package lshh.apirepository.orm.api.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lshh.apirepository.orm.RegistedInfo;

@Entity
@Table(name="api_query_argument")
public class QueryArgument extends RegistedInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name="query_parameter_id")
    String queryPrameterId;
    @Column(name="request_history_id")
    String requestHistoryId;
    String contents;
}
