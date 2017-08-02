package com.white.service;



import com.white.entity.system.SysMenu;
import com.white.entity.system.SysUser;

import java.util.List;

/**
 * 系统管理，包括用户、角色、菜单.
 */
public interface SystemService {

    // 根据登录名获取用户
    SysUser getUserByUserName(String username);

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

    int addMenu(SysMenu sysMenu);

}
