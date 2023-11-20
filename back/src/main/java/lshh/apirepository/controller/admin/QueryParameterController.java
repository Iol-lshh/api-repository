package lshh.apirepository.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import lshh.apirepository.dto.api.QueryParameterDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.query.QueryParameterService;

@RestController
@RequestMapping("/query_parameter")
public class QueryParameterController {
    
    @Autowired
    QueryParameterService queryParameterService;

    @PostMapping("/list")
    public Status save(@RequestBody List<QueryParameterDto> dtos) throws Exception{
        queryParameterService.save(dtos);
        return Status.OK;
    }

    @PostMapping("/one")
    public Status save(@RequestBody QueryParameterDto dto) throws Exception{
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        System.out.println(dto);
        queryParameterService.save(dto);
        return Status.OK;
    }
}
