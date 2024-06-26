package com.indocyber.Phoenix.controllers;

import com.indocyber.Phoenix.InfoUserLogin;
import com.indocyber.Phoenix.dtos.AuthRegisterDto;
import com.indocyber.Phoenix.models.Gender;
import com.indocyber.Phoenix.services.AccountService;
import com.indocyber.Phoenix.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthController {
    private  final AccountService accountService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final InfoUserLogin infoUserLogin;

    public AuthController(AccountService accountService, AuthService authService, PasswordEncoder passwordEncoder, InfoUserLogin infoUserLogin) {
        this.accountService = accountService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.infoUserLogin = infoUserLogin;
    }

    @GetMapping("register")
    public ModelAndView register(Model model) {
        var mv = new ModelAndView("auth/register");
        mv.addObject("genders", Gender.values());
        mv.addObject("dto", AuthRegisterDto.builder().build());
        if (model.containsAttribute("success")) {
            mv.addObject("success", true);
        }
        return mv;
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("dto") AuthRegisterDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders",Gender.values());
            return "auth/register";
        }
        authService.register(dto);
        return "redirect:/login";
    }

    @GetMapping("login")
    public ModelAndView login(@RequestParam(required = false) Boolean error, Model model) {
        ModelAndView mv = new ModelAndView("/auth/login");
        mv.addObject("roles",accountService.getRoleDropdown());
        if (model.containsAttribute("success")) {
            mv.addObject("success", true);
        }
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping("home")
    public ModelAndView redirectAfterLogin(Authentication authentication, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("currentUrl",request.getRequestURI());
        infoUserLogin.userDetailLogin(modelAndView);
        return modelAndView;

    }

    @PostMapping("/authenticating")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String roleName,
                               Model model) {
        UserDetails userDetails = authService.loadUserByUsername(username);
        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect berdasarkan peran
            if ("Administrator".equals(roleName)) {
                return "redirect:/home";
            } else if ("Guest".equals(roleName)) {
                return "redirect:/home";
            }
        }
        model.addAttribute("error", "Invalid credentials or role");
        return "login";
    }

}
