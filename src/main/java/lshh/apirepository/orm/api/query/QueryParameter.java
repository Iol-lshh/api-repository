package lshh.apirepository.orm.api.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Table(name = "api_query_parameter")
@Entity
public class QueryParameter extends RegistedInfo {
    @Id
    int id;

    String name;
    String description;
    String type;
    boolean isOptional;

    @ManyToOne
    @JoinColumn(name="query_id")
    Query query;
}
