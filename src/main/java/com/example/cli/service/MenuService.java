package com.example.cli.service;

import com.example.cli.domain.common.TreeNode;
import com.example.cli.entity.Menu;
import com.example.cli.repository.MenuRepository;
import com.example.cli.utils.TreeUtils;
import com.example.cli.utils.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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

    public List<TreeNode> getMenuList(){
        List<Menu> list=menuRepository.findAll();
        List<TreeNode> treeNodes=list.stream()
                .map(menu -> {
                    TreeNode treeNode=new TreeNode();
                    treeNode.setId(menu.getId());
                    treeNode.setParentId(menu.getParentId());
                    treeNode.setTitle(menu.getPermissionName());
                    treeNode.setKey(menu.getId());
                    treeNode.setIcon(menu.getIcon());
                    treeNode.setSort(menu.getSort());
                    if(menu.getParentId()!=null){
                        treeNode.setGroup(true);
                    }
                    return treeNode;
                })
                .collect(Collectors.toList());
        List<TreeNode> list1=TreeUtils.getMenuTreeList(treeNodes);
        setEmpty2Null(list1);
        return list1;
    }

    public void setEmpty2Null(List<TreeNode> list){
        for(TreeNode node:list){
            if(node.getChildren().size()==0){
                node.setChildren(null);
            }else{
                setEmpty2Null(node.getChildren());
            }

        }
    }



    public Menu getMenu(Integer id){
        return menuRepository.getOne(id);
    }

    public void saveMenu(Menu menu){
        if(StringUtils.isEmpty(menu.getId())){
            if(StringUtils.isEmpty(menu.getType())){
                menu.setType(2);
            }
            if(StringUtils.isEmpty(menu.getSort())){
                menu.setSort(1);
            }
            menuRepository.save(menu);
        }else {
            Menu target=menuRepository.getOne(menu.getId());
            MyBeanUtils.copyProperties(menu,target);
            menuRepository.save(target);
        }

    }

    public void delMenu(Integer id){
        menuRepository.deleteById(id);
    }





}
