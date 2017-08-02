package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysMenu;
import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import com.white.util.BootStrapPage;
import com.white.util.ResultUtil;
import com.white.web.exception.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by White on 2017/7/24.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SystemService systemService;

//    @RequestMapping("/list_parent_menu")
//    @ResponseBody
//    public BootStrapPage<SysMenu> listParerntMenu(HttpServletRequest request) {
//        BootStrapPage<SysMenu> bootStrapPage = new BootStrapPage<SysMenu>(request);
//        PageHelper.startPage(bootStrapPage.getPageNumber(),bootStrapPage.getPageSize());
////        List<SysMenu> list = systemService.getAllMenus();
//        List<SysMenu> list = systemService.getParentMenu();
//        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
//
//        bootStrapPage.setTotal((int) pageInfo.getTotal());
//        bootStrapPage.setRows(pageInfo.getList());
//        return bootStrapPage;
//    }
//
//    @RequestMapping("/list_chid_menu")
//    @ResponseBody
//    public BootStrapPage<SysMenu> listChidMenu(HttpServletRequest request, String parentId) {
//        BootStrapPage<SysMenu> bootStrapPage = new BootStrapPage<SysMenu>(request);
//        PageHelper.startPage(bootStrapPage.getPageNumber(),bootStrapPage.getPageSize());
//        List<SysMenu> list = systemService.getChildMenuByParentId(parentId);
//        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
//
//        bootStrapPage.setTotal((int) pageInfo.getTotal());
//        bootStrapPage.setRows(pageInfo.getList());
//        return bootStrapPage;
//    }

    @GetMapping("/list_all_menu")
    public BootStrapPage<SysMenu> listAllMenu(HttpServletRequest request) {
        BootStrapPage<SysMenu> bootStrapPage = new BootStrapPage<SysMenu>(request);
        PageHelper.startPage(bootStrapPage.getPageNumber(),bootStrapPage.getPageSize());
        List<SysMenu> list = null;
        String menuName = request.getParameter("menuName");
        if (menuName!= null && menuName.equals("")) {
            list = systemService.getAllMenus();
        }else {
            list = systemService.getMenuByMenuName(menuName);
        }
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        bootStrapPage.setTotal((int) pageInfo.getTotal());
        bootStrapPage.setRows(pageInfo.getList());
        return bootStrapPage;
    }

    @PostMapping("/addMenu")
    public  Result<SysMenu> addMenu(@Valid SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
       return ResultUtil.success();
    }

    @PostMapping("/deleteMenu")
    public void deleteMenu(String ids) {
        String[] id = ids.split(",");
        systemService.deleteMenu(id);
    }

}
