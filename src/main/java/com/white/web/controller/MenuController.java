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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/list_all_menu", method = RequestMethod.GET)
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result<SysMenu> addMenu(@ModelAttribute SysMenu sysMenu) {
        systemService.addMenu(sysMenu);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Result<SysMenu> updateMenu(@ModelAttribute SysMenu sysMenu) {
        systemService.update(sysMenu);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<SysMenu> deleteMenu(@PathVariable String id) {
        systemService.deleteMenu(id);
        return ResultUtil.success();
    }
}
