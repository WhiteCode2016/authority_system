package com.white.web.controller;

import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by White on 2017/8/3.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<SysUser> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<SysUser> sysUsers = systemService.getAllUsers();
        return sysUsers;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute SysUser sysUser) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        systemService.addUser(sysUser);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public SysUser getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return systemService.getUser(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable String id, @ModelAttribute SysUser sysUser) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        systemService.updateUser(sysUser);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        systemService.deleteUser(id);
        return "success";
    }
}
