package lshh.apirepository.orm.api.router;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;
import lshh.apirepository.orm.api.resource.Query;
import lshh.apirepository.orm.auth.AuthorizedRouter;

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Entity
public class Router extends RegistedInfo{
    @Id
    int id;

    String name;
    String path;
    String description;

    @ManyToOne
    @JoinColumn(name = "query_id")
    Query query;

    @OneToMany
    Collection<AuthorizedRouter> authorizedRouters;
}
