package lshh.apirepository.orm.api.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Integer>{

    List<Query> findByResourcerId(int resourcerId);
    
}
