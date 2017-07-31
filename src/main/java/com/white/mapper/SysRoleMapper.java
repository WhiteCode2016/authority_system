package com.white.mapper;

import com.white.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色DAO接口
 * Created by White on 2017/7/19.
 */
@Mapper
public interface SysRoleMapper {

    // 通过userId获取用户所拥有的角色
    List<SysRole> getRolesByUserId(String userId);
}
