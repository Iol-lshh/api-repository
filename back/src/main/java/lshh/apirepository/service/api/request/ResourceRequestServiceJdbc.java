package lshh.apirepository.service.api.request;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lshh.apirepository.common.pipeline.PipelineContext;
import lshh.apirepository.common.pipeline.PipelineManager;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.dto.request.ResourceRequestDto;
import lshh.apirepository.orm.api.pipeline.PipelineInfo;
import lshh.apirepository.orm.api.pipeline.PipelineInfoRepository;
import lshh.apirepository.orm.api.pipeline.PipelineStepInfoRepository;
import lshh.apirepository.orm.api.query.QueryRepository;
import lshh.apirepository.orm.api.resourcer.ResourcerInfoRepository;
import lshh.apirepository.orm.api.router.Router;
import lshh.apirepository.orm.api.router.RouterRepository;

@Service
public class ResourceRequestServiceJdbc implements ResourceRequestService{

    @Autowired
    RouterRepository routerRepository;
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    ResourcerInfoRepository resourcerInfoRepository;
    @Autowired
    PipelineInfoRepository pipelineInfoRepository;
    @Autowired
    PipelineStepInfoRepository pipelineStepInfoRepository;

    @Autowired
    PipelineManager pipelineManager;

    @Override
    public Optional<Object> getByPath(String path) throws Exception {
        
        List<QueryArgumentDto<Object>> argumentDtos = List.of();
        return get(new ResourceRequestDto()
            .path(path)
            .aruments(argumentDtos));
    }

    @Override
    @Transactional
    public Optional<Object> get(ResourceRequestDto dto) throws Exception {
        
        Router router = routerRepository.findByPath(dto.path())
            .orElseThrow(()->new Exception("제공하지 않는 경로"));

        PipelineInfo pipelineInfo = pipelineInfoRepository.findById(router.pipelineId())
            .orElseThrow(()->new Exception("제공하지 않는 파이프라인"));

        PipelineContext pipeline = pipelineManager.createContext(pipelineInfo.id(), dto.aruments());
        pipeline.process();

        return Optional.of(pipeline.returnList());
    }
    
}
