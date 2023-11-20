package lshh.apirepository.orm.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Requester {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
}
