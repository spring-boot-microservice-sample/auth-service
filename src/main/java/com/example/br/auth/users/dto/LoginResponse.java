package com.example.br.auth.users.dto;

import com.example.br.auth.users.User;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class LoginResponse {

    private User user;

    private String token;
}
