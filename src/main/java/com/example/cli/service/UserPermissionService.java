package com.example.cli.service;

import com.example.cli.constant.CommonConstant;
import com.example.cli.domain.common.LoginForm;
import com.example.cli.domain.common.ResponseBean;
import com.example.cli.entity.*;
import com.example.cli.exception.AuthException;
import com.example.cli.repository.RouteRepository;
import com.example.cli.repository.UserRepository;
import com.example.cli.utils.JWTUtil;
import com.example.cli.utils.cache.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liwei
 * @title: UserService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 13:54
 */
@Service
public class UserPermissionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CacheClient<User> cacheClient;
    @Value("${auth.expireTime}")
    private int expireTime;
    @Autowired
    private JWTUtil jwtUtil;

    public ResponseBean login(LoginForm loginForm){
        String username=loginForm.getUsername();
        String password=loginForm.getPassword();
        User userBean = getUserByLoginType(loginForm);
        if (userBean!=null&&userBean.getPassword().equals(password)) {
            Map<String,Object> map=new HashMap<>();
            String token=jwtUtil.sign(userBean.getId(), username);
            map.put("token",token);
            cacheClient.set(token,userBean,expireTime);
            return new ResponseBean(200, "Login success",map);
        } else {
            throw new AuthException(CommonConstant.LOGIN_EXCEPTION, "用户名或密码错误");
        }
    }


    public void logout(String token){
        cacheClient.remove(token);
    }


    public User getUser(String name){
        return userRepository.findByName(name);
    }

    private User getUserByLoginType(LoginForm loginForm){
        switch (loginForm.getLoginType()){
            case 0:
                //email
               return userRepository.findByEmail(loginForm.getUsername());
            case 1:
                //username
                return userRepository.findByName(loginForm.getUsername());
            case 2:
                //telephone
                return userRepository.findByPhone(loginForm.getMobile());
            default:
                return null;
        }

    }



}
