package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.dtos.reservationLog.ReservationSearchDto;
import com.indocyber.Phoenix.services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final InfoUserLogin infoUserLogin;

    public ReservationController(ReservationService reservationService, InfoUserLogin infoUserLogin) {
        this.reservationService = reservationService;
        this.infoUserLogin = infoUserLogin;
    }

    @GetMapping("")
    public ModelAndView index (ReservationSearchDto dto, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("reservationLog/index");
        mv.addObject("dto",dto);
        mv.addObject("reservations", reservationService.getAll(dto));
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);
        return mv;

    }

    @GetMapping("{id}")
    public  ModelAndView getId (@PathVariable String id,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("reservationLog/detail");
        mv.addObject("currentUrl",request.getRequestURI());
        mv.addObject("detail",reservationService.getId(id));
        infoUserLogin.userDetailLogin(mv);
        return mv;

    }
}
