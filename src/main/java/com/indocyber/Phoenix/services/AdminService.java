package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.admin.AdminRowDto;
import com.indocyber.Phoenix.models.Administrator;
import com.indocyber.Phoenix.repositories.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdministratorRepository administratorRepository;


    public AdminService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public List<AdminRowDto> getAll(){
        List<Administrator> administrator = administratorRepository.findAll();

        List<AdminRowDto> adminRowDtos = administrator.stream()
                .map(administrator1 -> AdminRowDto.builder()
                        .username(administrator1.getUsername())
                        .jobTitle(administrator1.getJobTitle())
                        .build())
                .toList();

        return adminRowDtos;

    }

    public Administrator getAdminActive(String username) {
        return administratorRepository.findById(username).orElseThrow(()->new IllegalArgumentException("not found username"));
//        return administratorRepository.findUsername(username);
    }
}
