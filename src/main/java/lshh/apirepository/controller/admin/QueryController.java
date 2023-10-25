package lshh.apirepository.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.dto.api.QueryViewDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.query.QueryParameterService;
import lshh.apirepository.service.api.query.QueryService;

@RequestMapping("/query")
@RestController()
public class QueryController {
    @Autowired
    QueryService queryService;

    @Autowired
    QueryParameterService queryParameterService;

    @GetMapping("/list/{resourcerId}")
    public List<QueryDto> findList(@PathVariable("resourcerId") int resourcerId){
        return queryService.findListByResource(resourcerId);
    }

    @GetMapping("/{queryId}")
    public QueryDto find(@PathVariable int queryId){
        return queryService.find(queryId)
            .orElse(null);
    }

    @GetMapping("/view/{queryId}")
    public QueryViewDto findView(@PathVariable int queryId) throws Exception{
        return queryService.findView(queryId);
    }

    @PostMapping("")
    @Transactional
    public Status save(@RequestBody QueryDto dto) throws Exception{
        queryService.save(dto);
        return Status.OK;
    }
}
