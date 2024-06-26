package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.AuthRegisterDto;
import com.indocyber.Phoenix.models.*;
import com.indocyber.Phoenix.repositories.AdministratorRepository;
import com.indocyber.Phoenix.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final GuestRepository guestRepository;
    private final AdministratorRepository administratorRepository;

    public AuthService( PasswordEncoder passwordEncoder, GuestRepository guestRepository, AdministratorRepository administratorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.guestRepository = guestRepository;
        this.administratorRepository = administratorRepository;
    }

    public void register(AuthRegisterDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password did not match");
        }

        var hashedPassword = passwordEncoder.encode(dto.getPassword());

        Gender gender;
        if (dto.getGender() == "Female"){
            gender = Gender.FEMALE;
        }else {
            gender = Gender.MALE;
        }

        Guest guest = Guest.builder()
                .username(dto.username)
                .password(hashedPassword)
                .firstName(dto.firstName)
                .middleName(dto.middleName)
                .lastName(dto.lastName)
                .birthDate(dto.birthDate)
                .gender(gender)
                .citizenship(dto.citizenship)
                .idNumber(dto.getIdNumber())
                .build();

        guestRepository.save(guest);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator admin = administratorRepository.findUsername(username);
        if (admin != null) {
//            return new User(admin.getUsername(),admin.getPassword(), getAuthorities("Administrator"));
            return MyAdminDetail.builder()
                    .administrator(admin)
                    .build();
        }

        Guest guest = guestRepository.findUsername(username);
        if (guest != null) {
//            return  new User(guest.getUsername(),guest.getPassword(),getAuthorities("Guest"));
            return MyGuestDetail.builder()
                    .guest(guest)
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return Collections.singletonList(new SimpleGrantedAuthority(role));
//    }


}
