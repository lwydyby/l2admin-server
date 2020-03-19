package com.example.cli.controller;

import com.example.cli.domain.common.ResponseBean;
import com.example.cli.entity.Route;
import com.example.cli.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwei
 * @title: RouteController
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 11:14
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteService routeService;


    @GetMapping
    public ResponseBean getRoute(@RequestParam("id") Integer id){
        return new ResponseBean(routeService.getRoute(id));
    }

    @PostMapping
    public ResponseBean saveRoute(@RequestBody Route route){
        routeService.saveRoute(route);
        return new ResponseBean("success");
    }

    @DeleteMapping
    public ResponseBean delRoute(@RequestParam("id") Integer id){
        routeService.delRoute(id);
        return new ResponseBean("success");
    }


}
