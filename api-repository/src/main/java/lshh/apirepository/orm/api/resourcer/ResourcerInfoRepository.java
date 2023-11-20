package lshh.apirepository.orm.api.resourcer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcerInfoRepository extends JpaRepository<ResourcerInfo, Integer>{

    Optional<ResourcerInfo> findOneByPath(String path);
    
}
