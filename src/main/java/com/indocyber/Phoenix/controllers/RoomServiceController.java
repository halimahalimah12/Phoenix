package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.dtos.room.RoomSearchDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceRosterUpsertDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceSearchDto;
import com.indocyber.Phoenix.models.RoomType;
import com.indocyber.Phoenix.services.CleaningService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("roomService")
public class RoomServiceController {
    private final CleaningService cleaningService;
    private final InfoUserLogin infoUserLogin;

    public RoomServiceController(CleaningService cleaningService, InfoUserLogin infoUserLogin) {
        this.cleaningService = cleaningService;
        this.infoUserLogin = infoUserLogin;
    }

    @GetMapping("")
    public ModelAndView index(RoomServiceSearchDto dto , HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("roomService/index");
        mv.addObject("roomServices",cleaningService.getAll(dto));
        mv.addObject("dto",dto);
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);

        return mv;
    }

    @GetMapping("{id}/roster")
    public ModelAndView inventory(@PathVariable String id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("roomService/indexRoster");
        mv.addObject("dto",cleaningService.getRoomServiceByNumber(id));
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }

    @GetMapping("{id}/update")
    public ModelAndView updateRoster(@PathVariable String id,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("roomService/upsertRoster");
        mv.addObject("id",id);
        mv.addObject("dto",cleaningService.getForUpdate(id));
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }
    @PostMapping("{id}/upsert")
    public String saveRoster(@PathVariable String id,RoomServiceRosterUpsertDto dto){
        cleaningService.save(dto);

        return "redirect:/roomService/{id}/roster";
    }




}
