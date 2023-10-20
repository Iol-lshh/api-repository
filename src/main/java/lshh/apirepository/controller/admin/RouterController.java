package lshh.apirepository.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.router.RouterService;

@RequestMapping("/router")
@RestController
public class RouterController {
    @Autowired
    RouterService routerService;

    @PostMapping()
    public Status save(RouterDto dto){
        routerService.save(dto);
        return Status.OK;
    }

    @GetMapping("/list/{pageSize}/{pageNo}")
    public List<RouterDto> findList(int pageSize, int pageNo){
        return routerService.findList(pageSize, pageNo);
    }

    @GetMapping("/{id}")
    public RouterDto find(int id){
        return routerService.find(id);
    }
    
}
