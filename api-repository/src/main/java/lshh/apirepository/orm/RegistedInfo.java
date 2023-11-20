package lshh.apirepository.orm;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class RegistedInfo {
    LocalDateTime created;
    LocalDateTime deleted;
    boolean isEnabled;
}
