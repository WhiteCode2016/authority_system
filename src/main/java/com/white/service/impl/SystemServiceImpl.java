package com.white.service.impl;

import com.white.entity.SysMenu;
import com.white.entity.SysUser;
import com.white.mapper.SysMenuMapper;
import com.white.mapper.SysRoleMapper;
import com.white.mapper.SysUserMapper;
import com.white.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统管理实现类,包括用户、角色、菜单.
 */
@Service
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

    // 系统用户Mapper
    @Autowired
    private SysUserMapper sysUserMapper;
    // 系统角色Mapper
    @Autowired
    private SysRoleMapper sysRoleMapper;
    // 系统菜单Mapper
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Cacheable(value = "test")
    public SysUser getUserByUserName(String username) {
        SysUser sysUser = sysUserMapper.getUserByUserName(username);
        if (sysUser == null) {
            return null;
        }
        String userId = sysUser.getId();
        // 获取用户所拥有的角色列表
        sysUser.setRoles(sysRoleMapper.getRolesByUserId(userId));

        List<SysMenu> menuList = null;

        if (SysUser.ADMIN_USER_ID.equals(userId)) {
            // 超级管理员
            menuList = sysMenuMapper.getAllMenus();
        } else {
            menuList = sysMenuMapper.getMenusByUserId(userId);
        }
        // 获取用户所拥有的菜单列表
        sysUser.setMenus(menuList);
        return sysUser;
    }

    @Override
    public List<SysMenu> getAllMenus() {
        return sysMenuMapper.getAllMenus();
    }

    @Override
    public SysMenu getMenuByMenuName(String menuName) {
        return sysMenuMapper.getMenuByMenuName(menuName);
    }
}
