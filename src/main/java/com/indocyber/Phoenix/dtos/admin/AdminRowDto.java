package com.indocyber.Phoenix.dtos.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminRowDto {
    private final String username;
    private final String jobTitle;
}
