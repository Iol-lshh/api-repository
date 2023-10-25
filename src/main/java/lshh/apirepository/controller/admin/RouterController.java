package lshh.apirepository.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lshh.apirepository.dto.api.RouterDto;
import lshh.apirepository.dto.api.RouterViewDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.service.api.router.RouterService;

@RequestMapping("/router")
@RestController
public class RouterController {
    @Autowired
    RouterService routerService;

    @PostMapping()
    public Status save(@RequestBody RouterDto dto){
        routerService.save(dto);
        return Status.OK;
    }

    @GetMapping("/list/{pageSize}/{pageNo}")
    public List<RouterDto> findList(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo){
        return routerService.findList(pageSize, pageNo);
    }

    @GetMapping("/{routerId}")
    public RouterDto find(@PathVariable int routerId){
        return routerService.find(routerId)
            .orElse(null);
    }

    @GetMapping("/view/{routerId}")
    public RouterViewDto findView(@PathVariable int routerId) throws Exception{
        return routerService.findView(routerId);
    }

    @GetMapping("/list/all")
    public List<RouterDto> findAll(){
        return routerService.findAll();
    }
    
}
