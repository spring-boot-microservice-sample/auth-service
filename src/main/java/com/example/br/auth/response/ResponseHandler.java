package com.example.br.auth.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> response(String msg, HttpStatus status, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", obj);
        map.put("message", msg);

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> response(String msg, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", msg);

        return new ResponseEntity<>(map, status);
    }
}

