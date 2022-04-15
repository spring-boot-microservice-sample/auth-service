package com.example.br.auth.users.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter @ToString
@Builder
public class LoginRequest {

    private String email;
    private String password;
}
