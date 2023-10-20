package lshh.apirepository.service.api.router;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.RouterDto;
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

    @Override
    public Status save(RouterDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<RouterDto> findList(int pageSize, int pageNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findList'");
    }

    @Override
    public RouterDto find(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }
    
}
