### 说明

1. 为前端项目提供了后端的接口

2. 登录token采用JWT来生成，权限的拦截控制使用shiro来实现（暂未未使用redis缓存）

3. 后端目前提供了对用户，角色，菜单的新建编辑，对接口的控制并不打算使用前端来控制，而改为
使用shiro的注解来控制

4. 后端的菜单和路由基本可以实现无限级的配置，但由于方法未优化，层数过多可能处理会变慢


### 关联项目

[springboot后端服务](https://github.com/lwydyby/springboot-cli-server)

[vue前端项目](https://github.com/lwydyby/springboot-cli-web)

[项目脚手架](https://github.com/lwydyby/springboot-cli-generator)
