package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.services.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final InfoUserLogin infoUserLogin;

    public InventoryController(InventoryService inventoryService, InfoUserLogin infoUserLogin) {
        this.inventoryService = inventoryService;
        this.infoUserLogin = infoUserLogin;
    }

    @GetMapping("")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("inventory/index");
        mv.addObject("inventoryList",inventoryService.getAll());
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }
}
