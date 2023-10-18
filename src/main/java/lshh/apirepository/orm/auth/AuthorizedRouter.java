package lshh.apirepository.orm.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lshh.apirepository.orm.api.router.Router;

@Entity
public class AuthorizedRouter {
    @Id
    int id;

    @ManyToOne
    @JoinColumn(name="reouter_id")
    Router router;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    Requester requester;
}
