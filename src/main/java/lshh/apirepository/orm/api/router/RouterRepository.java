package lshh.apirepository.orm.api.router;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouterRepository extends JpaRepository<Router, Integer>{
    Optional<Router> findByPath(String path);
}
