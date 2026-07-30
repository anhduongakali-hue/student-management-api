package com.example.tutor.Controller;

import com.example.tutor.DTO.AuthReqtDTO;
import com.example.tutor.DTO.AuthRsponDTO;
import com.example.tutor.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthRsponDTO> login(@RequestBody AuthReqtDTO request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
