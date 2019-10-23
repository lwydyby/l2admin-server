package com.example.cli.controller;

import com.example.cli.domain.ResponseBean;
import com.example.cli.entity.Menu;
import com.example.cli.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwei
 * @title: MenuController
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 14:28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseBean getMenuList(){
        return new ResponseBean(menuService.getMenuList());
    }

    @GetMapping("{id}")
    public ResponseBean getMenu(@PathVariable String id){
        return new ResponseBean(menuService.getMenu(id));
    }

    @PostMapping("/save")
    public ResponseBean saveMenu(@RequestBody Menu menu){
        menuService.saveMenu(menu);
        return new ResponseBean("success");
    }

    @DeleteMapping("{id}")
    public ResponseBean delMenu(@PathVariable String id){
        menuService.delMenu(id);
        return new ResponseBean("success");
    }

}
