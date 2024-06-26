package com.indocyber.Phoenix.dtos;

import com.indocyber.Phoenix.models.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuthRegisterDto {
    @NotNull
    @NotBlank(message = "Please, Entry Your username!")
    public String username;
    @NotBlank(message = "Please, Entry Your password!")
    @Size(min = 8, max = 20, message = "Please enter a password between 8 and 20!")
    public String password;
    @NotBlank(message = "Please, Entry Your confirm password!")
    @Size(min = 8, max = 20, message = "Please enter a password between 8 and 20!")
    public String confirmPassword;
    public Integer id;
    @NotNull
    @NotBlank(message = "Please, Entry Your Name!")
    public String firstName;
    public String middleName;
    public String lastName;
    public LocalDate birthDate;
    public String gender;
    public String citizenship;
    public String idNumber;
}
