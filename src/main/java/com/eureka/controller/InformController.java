package com.eureka.controller;

import com.eureka.controller.utils.R;
import com.eureka.domain.Inform;
import com.eureka.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inform")
public class InformController {
    @Autowired
    private InformService informService;

    @GetMapping("/{type}")
    public Object getByType(@PathVariable String type){
        return informService.getByType(type);
    }

    @PostMapping
    public String save(@RequestBody Inform inform){
        boolean save = informService.save(inform);
        if(save) {
            return "success";
        }else{
            return "fail";
        }
    }

    @GetMapping
    public R getPage(@RequestParam("id") Integer id){
        return new R(true,informService.getById(id));
    }

    @PutMapping("/join/{id}")
    public R join(@PathVariable String id, @RequestParam("username") String username){
        if(informService.join(Integer.parseInt(id),username)) {
            return new R(true, "success");
        } else{
            return new R(false, "加入失败,请检查是否已经加入");
        }
    }

    @GetMapping("/leader/{leader}")
    public Object getByLeader(@PathVariable String leader){
        return informService.getByLeader(leader);
    }

    @GetMapping("/member/{member}")
    public Object getByMember(@PathVariable String member){
        return informService.getByMember(member);
    }
}
