package lshh.apirepository.orm.api.router;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;
import lshh.apirepository.orm.auth.AuthorizedRouter;

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Table(name = "api_router")
@Entity
public class Router extends RegistedInfo{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
    String path;
    String description;

    @Column(name = "query_id")
    Integer queryId;

    @OneToMany
    Collection<AuthorizedRouter> authorizedRouters;
}
