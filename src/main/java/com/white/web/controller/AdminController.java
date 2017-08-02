package com.white.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by White on 2017/7/24.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    // 默认主页
    @RequestMapping("admin_default_index")
    public String adminDefault() {
        return "admin/default_index";
    }

}
