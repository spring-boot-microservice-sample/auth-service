package com.example.br.auth.users;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table ( name = "users" )
@AllArgsConstructor
@Getter @Setter @ToString
@RequiredArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( updatable = false, nullable = false )
    private Long user_id;

    @Column( nullable = false, updatable = false, unique = true )
    private String email;

    @Column( nullable = false )
    private String password;

    private String name;
}
