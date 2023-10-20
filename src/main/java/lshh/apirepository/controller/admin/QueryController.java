package lshh.apirepository.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.query.QueryService;

@RequestMapping("/query")
@RestController()
public class QueryController {
    @Autowired
    QueryService queryService;

    @GetMapping("/list/{resourceId}")
    public List<QueryDto> findList(int resourceId){
        return queryService.findListByResource(resourceId);
    }

    @GetMapping("/{queryId}")
    public QueryDto find(int queryId){
        return queryService.find(queryId).orElseGet(null);
    }

    @PostMapping("/new")
    public Status save(QueryDto dto){
        queryService.save(dto);
        return Status.OK;
    }
}
