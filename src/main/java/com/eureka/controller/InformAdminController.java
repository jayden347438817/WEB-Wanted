package com.eureka.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eureka.controller.utils.R;
import com.eureka.domain.Inform;
import com.eureka.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eureka
 * @create 2022/8/31 20:47
 */
@RestController
@RequestMapping("/admin")
public class InformAdminController {
    @Autowired
    private InformService informService;

    @GetMapping
    public R getAll(){
        return new R(true,informService.list());
    }

    @PostMapping
    public R save(@RequestBody Inform inform){
        Boolean flag = informService.save(inform);
        return new R(flag, flag ? "添加成功" : "添加失败");
    }

    @PutMapping
    public R update(@RequestBody Inform inform){
        return new R(informService.updateById(inform),null);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        return new R(informService.removeById(id),null);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,informService.getById(id));
    }

    @GetMapping("/{page}/{size}")
    // Controller有实体类参数，Spring会自动从请求参数获取数据装配到实体类中
    // 虽然Book是从RequestParam中拿到的，但是如果使用@RequestParam注解，Book就不能为空了
    // 不写可以用，还可以不传Book的参数
    public R getPage(@PathVariable Integer page, @PathVariable Integer size, Inform inform){
        IPage<Inform> page1 = informService.getPage(page, size, inform);
        // 如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > page1.getPages()) {
            page1 =  informService.getPage((int)page1.getPages(), size, inform);
        }
        return new R(true,page1);
    }
}
