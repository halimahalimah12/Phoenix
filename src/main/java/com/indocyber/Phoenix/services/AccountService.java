package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.SelectListRoleDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService  {
    public List<SelectListRoleDto> getRoleDropdown(){
        List<SelectListRoleDto> roleDtos =new ArrayList<>();
        roleDtos.add(SelectListRoleDto.builder()
                .roleName("Administrator")
                .build());
        roleDtos.add(SelectListRoleDto.builder()
                .roleName("Guest")
                .build());
        return roleDtos;
    }


}
