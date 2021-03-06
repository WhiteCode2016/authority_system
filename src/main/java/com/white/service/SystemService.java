package com.white.service;



import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.white.entity.system.SysMenu;
import com.white.entity.system.SysUser;

import java.util.List;

/**
 * 系统管理，包括用户、角色、菜单.
 */
public interface SystemService {

    // 根据登录名获取用户
    SysUser getUserByUserName(String username);

    // 获取用户列表
    List<SysUser> getAllUsers();

    SysUser getUser(String id);

    // 添加用户
    void addUser(SysUser sysUser);

    // 编辑用户
    void updateUser(SysUser sysUser);

    // 删除用户
    void deleteUser(String id);

    // 获取所有菜单
    List<SysMenu> getAllMenus();

    // 根据菜单名查询
    List<SysMenu> getMenuByMenuName(String menuName);

    // 获取所有的父级菜单
    List<SysMenu> getParentMenu();

    // 通过parentId获取其子级菜单
    List<SysMenu> getChildMenuByParentId(String parentId);

    // 批量删除菜单
    void deleteMenu(String[] id);

    // 单个删除菜单
    void deleteMenu(String id);

    // 添加菜单
    void addMenu(SysMenu sysMenu);

    // 编辑菜单
    void update(SysMenu sysMenu);

    // 通过Id获取菜单
    SysMenu getMenu(String id);
}
