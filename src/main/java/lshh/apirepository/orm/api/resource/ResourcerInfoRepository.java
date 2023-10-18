package lshh.apirepository.orm.api.resource;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcerInfoRepository extends JpaRepository<ResourcerInfo, Integer>{

    Optional<ResourcerInfo> findOneByPath(String path);
    
}
