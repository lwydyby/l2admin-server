#l2-admin 后台管理系统

[![Build Status](https://www.travis-ci.org/lwydyby/springboot-cli-server.svg?branch=master)](https://www.travis-ci.org/lwydyby/springboot-cli-server)

###项目简介
l2admin 基于 Spring Boot 2.1.9 、 Jpa、mybatis, JWT、Shiro、Vue的前后端分离的后台管理系统， 
权限控制的方式为RBAC，支持一键生成前后端代码(采用golang编写的命令行工具)，支持前端菜单动态路由。

项目同时支持了mybatis和jpa，意在简单的查询使用jpa，复杂的多表联查使用mybatis。同时项目将依赖控制尽可能少，以便更简单的二次开发


### 项目源码

[springboot后端服务](https://github.com/lwydyby/springboot-cli-server)

[vue前端项目](https://github.com/lwydyby/springboot-cli-web)

[项目脚手架(web一键生成工具已废弃)](https://github.com/lwydyby/springboot-cli-generator)

[命令行脚手架](https://github.com/lwydyby/generator-cli)

### 系统功能

1. 用户管理：提供用户的相关配置

2. 角色管理：对权限与菜单进行分配

3. 菜单管理：已实现菜单动态路由，后端可配置化，支持多级菜单

4. 路由管理：已实现动态路由，后端可配置化，支持多级路由

5. 代码生成：高灵活度一键生成前后端代码（且可以复用于任何可以归纳为模板的工程代码，只需自己编写模板）,如果是单表的
增删改查，项目模板可以减少百分之99的工作任务


