package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.services.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminCotroller {
    private final AdminService adminService;
    private final InfoUserLogin infoUserLogin;

    public AdminCotroller(AdminService adminService, InfoUserLogin infoUserLogin) {
        this.adminService = adminService;
        this.infoUserLogin = infoUserLogin;
    }

    @GetMapping("")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/index");
        mv.addObject("adminList", adminService.getAll());
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }
}
