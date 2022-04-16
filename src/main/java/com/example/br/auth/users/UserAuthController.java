package com.example.br.auth.users;

import com.example.br.auth.response.ResponseHandler;
import com.example.br.auth.users.dto.LoginRequest;
import com.example.br.auth.users.dto.LoginResponse;
import com.example.br.auth.users.dto.RegisterRequest;
import com.example.br.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8, new SecureRandom());

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

        Optional<User> userByEmail = userRepository.findByEmail(loginRequest.getEmail());

        if(userByEmail.isEmpty()) {
            return ResponseHandler.response("Entered User Email does not exists", HttpStatus.BAD_REQUEST );
        }

        User user = userByEmail.get();
        boolean isPasswordCorrect = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!isPasswordCorrect) {
            return ResponseHandler.response("Password is incorrect", HttpStatus.BAD_REQUEST );
        }

        try {
            String token = jwtUtil.generateToken(user.getUser_id());

            LoginResponse responseData = LoginResponse.builder()
                    .user_id(user.getUser_id())
                    .email(user.getEmail())
                    .name(user.getEmail())
                    .token(token)
                    .build();

            return ResponseHandler.response(
                    "User Login Successful",
                    HttpStatus.OK,
                    responseData
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseHandler.response("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {

        if( userRepository.existsByEmail(registerRequest.getEmail()) ) {
            return ResponseHandler.response("User Email already exists", HttpStatus.BAD_REQUEST );
        }

        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .build();

        try {
            userRepository.save(newUser);
            return ResponseHandler.response(
                    registerRequest.getName() + " User added successfully",
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }



}
