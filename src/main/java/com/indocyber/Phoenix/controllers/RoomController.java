package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.dtos.room.RoomSearchDto;
import com.indocyber.Phoenix.dtos.room.RoomUpsertRequestDto;
import com.indocyber.Phoenix.models.RoomType;
import com.indocyber.Phoenix.services.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("rooms")
public class RoomController {

    private RoomService roomService;
    private  InfoUserLogin infoUserLogin;

    public RoomController(RoomService roomService, InfoUserLogin infoUserLogin) {
        this.roomService = roomService;
        this.infoUserLogin = infoUserLogin;
    }


    @GetMapping("")
    public ModelAndView index(RoomSearchDto dto, Model model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("room/index");
        mv.addObject("roomList",roomService.getAll(dto));
        mv.addObject("typeDropdown", RoomType.values());
        mv.addObject("dto",dto);
        mv.addObject("currentUrl",request.getRequestURI());


        if(model.containsAttribute("success")){
            mv.addObject("success",true);
        }
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }

    @GetMapping("insert")
    public ModelAndView insert(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("room/upsert");
        mv.addObject("dto",RoomUpsertRequestDto.builder().build());
        mv.addObject("typeDropdown", RoomType.values());
        infoUserLogin.userDetailLogin(mv);
        mv.addObject("currentUrl",request.getRequestURI());

        return mv;
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("dto") RoomUpsertRequestDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,HttpServletRequest request){
        if (bindingResult.hasErrors()){
            model.addAttribute("typeDropdown", RoomType.values());
            model.addAttribute("currentUrl",request.getRequestURI());
            infoUserLogin.userDetailLoginModel(model);
            return "room/upsert";
        }
        roomService.save(dto);
        redirectAttributes.addFlashAttribute("success",true);

        return "redirect:/rooms";
    }

    @GetMapping("{id}/update")
    public ModelAndView update(@PathVariable String id,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("room/upsert");
        mv.addObject("dto",roomService.getRoomNumber(id));
        mv.addObject("typeDropdown", RoomType.values());
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }

    @GetMapping("{id}/inventories")
    public ModelAndView inventory(@PathVariable String id,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("room/item");
        mv.addObject("dto",roomService.getRoomInfoByNumber(id));
        mv.addObject("inventories",roomService.findInventory(id));
        mv.addObject("currentUrl",request.getRequestURI());
        mv.addObject("inventoryDropdown",roomService.listInventories());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }




}
