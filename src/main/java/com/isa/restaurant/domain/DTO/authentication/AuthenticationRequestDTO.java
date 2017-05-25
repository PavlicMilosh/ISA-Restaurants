package com.isa.restaurant.domain.DTO.authentication;

import com.sun.javafx.sg.prism.NGShape;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO extends ModelBaseDTO
{
    private static final long serialVersionUID = 6624726180748515507L;
    private String email;
    private String password;

}
