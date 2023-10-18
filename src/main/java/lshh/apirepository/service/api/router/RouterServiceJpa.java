package lshh.apirepository.service.api.router;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;

@Service
public class RouterServiceJpa implements RouterService {

    @Autowired
    RouterRepository routerRepository;

    @Transactional
    public Optional<Router> findEntity(int id) {
        return routerRepository.findById(id);
    }
    
}
