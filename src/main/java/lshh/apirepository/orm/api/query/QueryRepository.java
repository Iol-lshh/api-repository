package lshh.apirepository.orm.api.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lshh.apirepository.orm.api.resourcer.ResourcerInfo;

public interface QueryRepository extends JpaRepository<Query, Integer>{

    List<Query> findByResourcerInfo(ResourcerInfo resourcerInfo);
    
}
