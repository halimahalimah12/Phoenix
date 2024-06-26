package com.indocyber.Phoenix;

import com.indocyber.Phoenix.models.Administrator;
import com.indocyber.Phoenix.models.Guest;
import com.indocyber.Phoenix.services.AdminService;
import com.indocyber.Phoenix.services.GuestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Component
public class InfoUserLogin {
    private final AdminService adminService;
    private final GuestService guestService;

    public InfoUserLogin(AdminService adminService, GuestService guestService) {
        this.adminService = adminService;
        this.guestService = guestService;
    }


    public void userDetailLogin(ModelAndView mv) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String nama = authentication.getName();
        var authorization = authentication.getAuthorities();
        boolean isAdmin = false;
        boolean isGuest = false;
        for (GrantedAuthority authority : authorization) {
            if (authority.getAuthority().equalsIgnoreCase("Administrator")) {
                isAdmin = true;
                break;
            } else if (authority.getAuthority().equalsIgnoreCase("Guest")) {
                isGuest = true;
                break;
            }
        }

        if (isAdmin) {
            Administrator administrator = adminService.getAdminActive(nama);
            mv.addObject("admin", administrator);
        } else {
            Guest guest = guestService.guestActive(nama);
            mv.addObject("guest", guest);
        }
    }

    public void userDetailLoginModel(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String nama = authentication.getName();
        var authorization = authentication.getAuthorities();
        boolean isAdmin = false;
        boolean isGuest = false;
        for (GrantedAuthority authority : authorization) {
            if (authority.getAuthority().equalsIgnoreCase("Administrator")) {
                isAdmin = true;
                break;
            } else if (authority.getAuthority().equalsIgnoreCase("Guest")) {
                isGuest = true;
                break;
            }
        }

        if (isAdmin) {
            Administrator administrator = adminService.getAdminActive(nama);
            model.addAttribute("admin", administrator);
        } else {
            Guest guest = guestService.guestActive(nama);
            model.addAttribute("guest", guest);
        }
    }

    public Guest guestActive() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String nama = authentication.getName();
        return guestService.guestActive(nama);
    }
}
