package lshh.apirepository.service.api.request;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.common.resourcer.JdbcResourcerContext;
import lshh.apirepository.common.resourcer.JdbcResourcerManager;
import lshh.apirepository.common.resourcer.ResourcerContext;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.dto.request.QueryRequestDto;
import lshh.apirepository.dto.request.ResourceRequestDto;
import lshh.apirepository.orm.api.query.Query;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;

@Service
public class ResourceRequestServiceJdbc implements ResourceRequestService{

    @Autowired
    JdbcResourcerManager manager;
    @Autowired
    RouterRepository routerRepository;
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    ResourcerInfoRepository resourcerInfoRepository;

    @Override
    public Optional<Object> getByPath(String path) throws Exception {
        
        List<QueryArgumentDto> argumentDtos = List.of();
        return get(new ResourceRequestDto()
            .path(path)
            .aruments(argumentDtos));
    }

    @Override
    @Transactional
    public Optional<Object> get(ResourceRequestDto dto) throws Exception {
        
        Router router = routerRepository.findByPath(dto.path())
            .orElseThrow(()->new Exception("제공하지 않는 경로"));

        Query query = queryRepository.findById(router.queryId())
            .orElseThrow(()->new Exception("제공하지 않는 쿼리"));

        if(query.resourcerId() == null){
            throw new Exception("제공하지 않는 리소서");
        }
        ResourcerContext resourcerContext = manager.getResourcer(query.resourcerId());

        switch(resourcerContext.type()){
            case DB:
                QueryRequestDto requestDto = new QueryRequestDto()
                    .query(query.contents())
                    .arguments(dto.aruments());
                    
                return Optional.of(((JdbcResourcerContext) resourcerContext).getResource(requestDto));
            
            case API:
                // todo - sub api (next version)
                return Optional.empty();

            default:
                return Optional.empty();
        }
        
    }
    
}
