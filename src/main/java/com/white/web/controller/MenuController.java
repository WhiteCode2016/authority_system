package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysMenu;
import com.white.service.SystemService;
import com.white.util.BootStrapPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/7/24.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("")
    public String adminInfo() {
        return "admin/menu/menu_view";
    }

    @RequestMapping("/list")
    @ResponseBody
    public BootStrapPage<SysMenu> list(HttpServletRequest request) {
        BootStrapPage<SysMenu> bootStrapPage = new BootStrapPage<SysMenu>(request);
        PageHelper.startPage(bootStrapPage.getPageNumber(),bootStrapPage.getPageSize());
        List<SysMenu> list = systemService.getAllMenus();
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        bootStrapPage.setTotal((int) pageInfo.getTotal());
        bootStrapPage.setRows(pageInfo.getList());
        return bootStrapPage;
    }
}
