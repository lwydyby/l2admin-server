package com.example.cli.controller;

import com.example.cli.domain.common.ResponseBean;
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

    @GetMapping("/tree")
    public ResponseBean getMenuList(){
        return new ResponseBean(menuService.getMenuList());
    }

    @GetMapping
    public ResponseBean getMenu(@RequestParam("id") Integer id){
        return new ResponseBean(menuService.getMenu(id));
    }

    @PostMapping
    public ResponseBean saveMenu(@RequestBody Menu menu){
        menuService.saveMenu(menu);
        return new ResponseBean("success");
    }

    @DeleteMapping("{id}")
    public ResponseBean delMenu(@PathVariable Integer id){
        menuService.delMenu(id);
        return new ResponseBean("success");
    }

}
