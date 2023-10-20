package lshh.apirepository.orm.api.query;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;
import lshh.apirepository.orm.api.resourcer.ResourcerInfo;
import lshh.apirepository.orm.api.router.Router;

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Table(name = "api_query")
@Entity
public class Query extends RegistedInfo{
    @Id
    int id;

    String name;
    String contents;
    String description;

    @ManyToOne
    @JoinColumn(name="resourcer_id")
    ResourcerInfo resourcerInfo;

    @OneToMany
    Collection<QueryParameter> queryParameters;

    @OneToMany
    Collection<Router> routers;
}