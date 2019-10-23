package com.example.cli.controller;

import com.example.cli.domain.ResponseBean;
import com.example.cli.domain.BaseSearch;
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
    public ResponseBean getRouteList(){
        return new ResponseBean(routeService.getRouteList());
    }

    @GetMapping("/{id}")
    public ResponseBean getRoute(@PathVariable("id") String id){
        return new ResponseBean(routeService.getRoute(id));
    }

    @PostMapping("/save")
    public ResponseBean saveRoute(@RequestBody Route route){
        routeService.saveRoute(route);
        return new ResponseBean("success");
    }

    @DeleteMapping("/{id}")
    public ResponseBean delRoute(@PathVariable String id){
        routeService.delRoute(id);
        return new ResponseBean("success");
    }


}
