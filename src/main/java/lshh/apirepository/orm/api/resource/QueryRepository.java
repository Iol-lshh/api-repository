package lshh.apirepository.orm.api.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Integer>{

    List<Query> findByResourcerInfo(ResourcerInfo resourcerInfo);
    
}
