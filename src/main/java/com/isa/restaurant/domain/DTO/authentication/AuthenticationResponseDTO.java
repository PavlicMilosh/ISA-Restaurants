package com.isa.restaurant.domain.DTO.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDTO extends ModelBaseDTO
{
    private String token;
    private Long id;
    private String email;
    private String role;
    private Boolean enabled;
}
