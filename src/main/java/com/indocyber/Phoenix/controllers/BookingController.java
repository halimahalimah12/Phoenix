package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.dtos.booking.BookingSearchDto;
import com.indocyber.Phoenix.dtos.booking.BookingUpsertDto;
import com.indocyber.Phoenix.models.Guest;
import com.indocyber.Phoenix.models.PaymentMethod;
import com.indocyber.Phoenix.models.ReservationMethod;
import com.indocyber.Phoenix.models.RoomType;
import com.indocyber.Phoenix.services.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;
    private final InfoUserLogin infoUserLogin;

    public BookingController(BookingService bookingService, InfoUserLogin infoUserLogin) {
        this.bookingService = bookingService;
        this.infoUserLogin = infoUserLogin;
    }


    @GetMapping("")
    public ModelAndView index(BookingSearchDto dto,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("booking/index");
        mv.addObject("roomList",bookingService.getAll(dto));
        mv.addObject("typeDropdown", RoomType.values());
        mv.addObject("dto",dto);
        mv.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(mv);

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView getId(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("booking/detail");
        mv.addObject("detail",bookingService.getId(id));
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }

    @GetMapping("{id}/insert")
    public  ModelAndView insert(@PathVariable String id){
        ModelAndView mv = new ModelAndView("booking/upsert");
        mv.addObject("detail",bookingService.getId(id));
        mv.addObject("paymentMethod", PaymentMethod.values());
        mv.addObject("reservationMethod", ReservationMethod.values());
        infoUserLogin.userDetailLogin(mv);
        return mv;
    }

    @PostMapping("upsert")
    public String upsert (BookingUpsertDto dto){
        bookingService.save(dto);
        return "redirect:/booking";
    }

    @GetMapping("/myRoom")
    public ModelAndView myRoom(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("booking/myRoom");
        Guest guest = infoUserLogin.guestActive();
        infoUserLogin.userDetailLogin(mv);
        mv.addObject("currentUrl",request.getRequestURI());
        mv.addObject("rooms",bookingService.myRoom(guest.getUsername()));
        return mv;
    }
}
