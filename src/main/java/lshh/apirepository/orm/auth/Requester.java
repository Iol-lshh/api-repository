package lshh.apirepository.orm.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Requester {
    @Id
    int id;

    String name;
}
