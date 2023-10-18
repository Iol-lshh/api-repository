package lshh.apirepository.orm;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class RegistedInfo {
    LocalDateTime created;
    LocalDateTime deleted;
    boolean isEnabled;
}
