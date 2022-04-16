package com.example.br.auth.users.dto;

import com.example.br.auth.users.User;
import lombok.*;

import javax.persistence.Column;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class LoginResponse {

    private Long user_id;
    private String email;
    private String name;

    private String token;
}
