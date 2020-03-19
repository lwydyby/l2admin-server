package com.example.cli.config;


import com.example.cli.constant.AuthEnum;
import com.example.cli.entity.User;
import com.example.cli.service.UserPermissionService;
import com.example.cli.utils.JWTUtil;
import com.example.cli.utils.RequestUserHolder;
import com.example.cli.utils.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private CacheClient<User> cacheClient;

    @Autowired
    private JWTUtil jwtUtil;

    @Value("${auth.expireTime}")
    private int expireTime;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = jwtUtil.getUsername(principals.toString());
        //TODO 后端shiro角色权限配置
        User user = userPermissionService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permission = new HashSet<>();
        permission.add("admin");
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 验证token是否合法,这里使用缓存实现了续签功能，
     * 如果重启了后端服务则续签失效
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        AuthEnum authEnum=jwtUtil.verify(token);
        if (authEnum.equals(AuthEnum.FAILED)) {
            throw new AuthenticationException("TOKEN验证失败,非法请求");
        }else if(authEnum.equals(AuthEnum.EXPIRE)){
            if(!cacheClient.isExist(token)){
                throw new AuthenticationException("TOKEN验证失败,非法请求");
            }
        }
        // 解密获得username，用于和数据库进行对比
        String username = jwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userPermissionService.getUser(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        cacheClient.set(token,userBean,expireTime);
        RequestUserHolder.add(userBean);
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
