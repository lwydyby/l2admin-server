-- 表格可以使用jpa自动生成,修改配置文件即可
-- 下面是基础的权限菜单数据

INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (1, NULL, NULL, 0, NULL, '/system', 'system', '系统管理', 2, '系统管理', 1, NULL);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (2, NULL, NULL, 0, 1, '/system/menu', 'systemMenu', '菜单管理', 1, '菜单管理', 1, 1);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (3, NULL, NULL, 0, NULL, '/dashboard', 'dashboard', '主页', 1, '主页', 1, NULL);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (4, NULL, NULL, NULL, 2, NULL, NULL, '编辑菜单', 1, NULL, 2, NULL);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (5, NULL, NULL, NULL, 1, '/system/role', 'systemRole', '角色管理', 2, '角色管理', 1, 6);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (6, NULL, NULL, NULL, 5, NULL, NULL, '增加角色', 1, NULL, 2, NULL);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (7, NULL, NULL, NULL, 1, '/system/user', 'systemUser', '用户管理', 3, '用户管理', 1, 7);
INSERT INTO `menu`(`id`, `default_check`, `icon`, `is_lock`, `parent_id`, `path`, `permission_id`, `permission_name`, `sort`, `title`, `type`, `route_id`) VALUES (8, NULL, NULL, NULL, 7, NULL, NULL, '增加用户', 1, NULL, 2, NULL);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 1);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 2);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 3);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 4);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 5);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (2, 7);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (3, 4);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (3, 2);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (3, 1);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (3, 6);
INSERT INTO `menu_role`(`role_id`, `function_id`) VALUES (3, 5);
INSERT INTO `role`(`id`, `create_time`, `deleted`, `description`, `name`, `role_id`, `status`, `create_id`) VALUES (2, '2020-03-19 09:07:45', 0, '管理员', '管理员', 'admin', 0, 1);
INSERT INTO `role`(`id`, `create_time`, `deleted`, `description`, `name`, `role_id`, `status`, `create_id`) VALUES (3, '2020-03-19 04:42:12', 0, '测试', '测试', NULL, 0, 1);
INSERT INTO `role_user`(`role_id`, `user_id`) VALUES (2, 1);
INSERT INTO `role_user`(`role_id`, `user_id`) VALUES (2, 2);
INSERT INTO `route`(`id`, `cache`, `component`, `component_path`, `is_lock`, `name`, `parent_id`, `path`, `permission`, `sort`, `title`) VALUES (1, NULL, 'menu', '@/views/other/TreeList', NULL, '菜单管理', NULL, '@/views/other/TreeList', NULL, 1, '菜单管理');
INSERT INTO `route`(`id`, `cache`, `component`, `component_path`, `is_lock`, `name`, `parent_id`, `path`, `permission`, `sort`, `title`) VALUES (6, NULL, 'role', '@/views/other/RoleList', NULL, '角色管理', NULL, '@/views/other/TreeList', NULL, 2, NULL);
INSERT INTO `route`(`id`, `cache`, `component`, `component_path`, `is_lock`, `name`, `parent_id`, `path`, `permission`, `sort`, `title`) VALUES (7, NULL, 'user', '@/views/other/UserList', NULL, '用户管理', NULL, '@/views/other/UserList', NULL, 3, NULL);
INSERT INTO `user`(`id`, `avatar`, `create_time`, `deleted`, `email`, `last_login_ip`, `last_login_time`, `name`, `password`, `phone`, `status`, `user_name`, `create_id`) VALUES (1, NULL, '2020-03-19 08:48:06', 0, NULL, NULL, NULL, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '17611450831', 0, NULL, 1);
INSERT INTO `user`(`id`, `avatar`, `create_time`, `deleted`, `email`, `last_login_ip`, `last_login_time`, `name`, `password`, `phone`, `status`, `user_name`, `create_id`) VALUES (2, NULL, '2020-03-19 06:10:48', 0, '123@123.com', NULL, NULL, 'test', 'e10adc3949ba59abbe56e057f20f883e', '123456', 0, NULL, 1);
