package com.example.cli.service;

import com.example.cli.entity.Menu;
import com.example.cli.repository.MenuRepository;
import com.example.cli.utils.TreeUtils;
import com.example.cli.utils.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: MenuService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 14:19
 */
@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getMenuList(){
        List<Menu> list=menuRepository.findAll();
        return TreeUtils.getMenuTreeList(list);
    }

    public Menu getMenu(String id){
        return menuRepository.getOne(id);
    }

    public void saveMenu(Menu menu){
        if(StringUtils.isEmpty(menu.getId())){
            menu.setLock(0);
            menuRepository.save(menu);
        }else {
            Menu target=menuRepository.getOne(menu.getId());
            MyBeanUtils.copyProperties(menu,target);
            menuRepository.save(target);
        }

    }

    public void delMenu(String id){
        menuRepository.deleteById(id);
    }





}
