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
    SysMenu getMenuByMenuName(String menuName);
}
