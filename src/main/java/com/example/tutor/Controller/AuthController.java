package com.example.tutor.Controller;

import com.example.tutor.DTO.LoginReqtDTO;
import com.example.tutor.DTO.RegisterReqtDTO;
import com.example.tutor.DTO.AuthRsponDTO;
import com.example.tutor.Service.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AuthRsponDTO> login(@Valid @RequestBody LoginReqtDTO request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReqtDTO request){

        return ResponseEntity.ok(authService.register(request));
    }
}
