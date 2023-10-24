package lshh.apirepository.orm.api.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lshh.apirepository.orm.RegistedInfo;

@Entity
@Table(name="api_resource_request")
public class ResourseRequest extends RegistedInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(name="requester_id")
    String requesterId;
    @Column(name = "router_id")
    String routerId;
    int state;

}
