package lshh.apirepository.orm.api.resourcer;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;
import lshh.apirepository.orm.api.query.Query;

@Accessors(chain = true, fluent = true)
@Getter
@Setter
@Entity
public class ResourcerInfo extends RegistedInfo{
    @Id
    int id;

    String name;
    String path;
    String description;

    String key;

    @OneToMany
    Collection<Query> queries;
}
