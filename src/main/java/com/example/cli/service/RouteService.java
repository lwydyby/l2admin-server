package com.example.cli.service;

import com.example.cli.domain.RouteInfo;
import com.example.cli.domain.RouteTree;
import com.example.cli.entity.Route;
import com.example.cli.repository.RouteRepository;
import com.example.cli.utils.TreeUtils;
import com.example.cli.utils.MyBeanUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: RouteService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 11:08
 */
@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public List<RouteTree> getRouteList(){
        List<Route> routes=routeRepository.findAllByLock(0);
        List<RouteTree> result=new ArrayList<>();
        for(Route route:routes){
            RouteTree routeInfo=new RouteTree();
            routeInfo.setId(route.getId());
            routeInfo.setLabel(route.getTitle());
            routeInfo.setSort(route.getSort());
            routeInfo.setParentId(route.getParentId());
            result.add(routeInfo);

        }
        return TreeUtils.getMenuTreeList(result);

    }


    public Route getRoute(String id){
        return routeRepository.getOne(id);
    }

    public void saveRoute(Route route){
        if(StringUtils.isEmpty(route.getId())){
            route.setLock(0);
            route.setCache(0);
            routeRepository.save(route);
        }else {
            Route target=routeRepository.getOne(route.getId());
            MyBeanUtils.copyProperties(route,target);
            routeRepository.save(target);
        }
    }

    public void delRoute(String id){
        routeRepository.deleteById(id);
    }
}
