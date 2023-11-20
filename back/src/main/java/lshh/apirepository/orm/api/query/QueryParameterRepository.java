package lshh.apirepository.orm.api.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryParameterRepository extends JpaRepository<QueryParameter, Integer> {

    List<QueryParameter> findByQueryId(int queryId);
    
}
