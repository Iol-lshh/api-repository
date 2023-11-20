package lshh.apirepository.orm.api.resourcer;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lshh.apirepository.orm.RegistedInfo;
import lshh.apirepository.orm.api.query.Query;

@Accessors(chain = true, fluent = true)
@Getter
@Setter
@Table(name="api_resourcer_info")
@Entity
public class ResourcerInfo extends RegistedInfo{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String name;
    String path;
    String description;

    @Column(name = "access_name")
    String accessName;
    String key;

    String driver;
    @Column(name="driver_class_name")
    String driverClassName;
}
