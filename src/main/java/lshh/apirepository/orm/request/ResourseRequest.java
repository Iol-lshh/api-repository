package lshh.apirepository.orm.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lshh.apirepository.orm.RegistedInfo;

@Entity
public class ResourseRequest extends RegistedInfo {
    @Id
    int id;

    String requesterId;
    String routerId;
    int state;

}
