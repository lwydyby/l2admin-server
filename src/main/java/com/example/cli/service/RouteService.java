package com.example.cli.service;

import com.example.cli.entity.Route;
import com.example.cli.repository.RouteRepository;
import com.example.cli.utils.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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



    public Route getRoute(Integer id){
        return routeRepository.getOne(id);
    }

    public void saveRoute(Route route){
        if(StringUtils.isEmpty(route.getId())){
            routeRepository.save(route);
        }else {
            Route target=routeRepository.getOne(route.getId());
            MyBeanUtils.copyProperties(route,target);
            routeRepository.save(target);
        }
    }

    public void delRoute(Integer id){
        routeRepository.deleteById(id);
    }
}
