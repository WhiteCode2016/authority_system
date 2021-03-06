package com.white;

import com.white.mapper.SysMenuMapper;
import com.white.mapper.SysRoleMapper;
import com.white.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(SpringbootApplicationTests.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Test
	public void sysUserMapperTest() {
//		System.out.println(sysUserMapper.getUserByUserName("admin"));
		System.out.println(sysUserMapper.get("1"));
//		SysUser sysUser = new SysUser();
////		sysUser.preInsert();
////		sysUser.setIsNewRecord(true);
//		sysUser.setId("2");
//		sysUser.setUsername("root");
//		sysUser.setPassword("123");
//		sysUser.setEnabled(true);
//		sysUser.setRemarks("This is Test");
//		sysUserMapper.insert(sysUser);
	}

	@Test
	public void sysRoleMapperTest() {
		System.out.println(sysRoleMapper.getRolesByUserId("1"));
	}

	@Test
	public void setSysMenuMapperTest() {
//		System.out.println(sysMenuMapper.getMenusByUserId("1"));
		System.out.println(sysMenuMapper.getAllMenus());
	}

	@Test
	public void deleteMenu(){
		sysMenuMapper.deleteMenu(new String[]{"50223b0d0c014e848296f7580df517e7"});
	}

}
