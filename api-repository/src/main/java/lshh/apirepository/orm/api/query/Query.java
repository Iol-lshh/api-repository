package lshh.apirepository.orm.api.query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;

@Accessors(chain = true, fluent = true)
@Setter
@Getter
@Table(name = "api_query")
@Entity
public class Query extends RegistedInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String contents;
    String description;

    @Column(name = "resourcer_id")
    Integer resourcerId;
}
