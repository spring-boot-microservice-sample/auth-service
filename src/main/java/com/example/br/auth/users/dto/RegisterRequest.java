package com.example.br.auth.users.dto;

import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter @ToString
@Builder
public class RegisterRequest {

    private String email;
    private String password;

    private String name;
}
