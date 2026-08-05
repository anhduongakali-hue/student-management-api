package com.example.tutor.Service;


import com.example.tutor.DTO.LoginReqtDTO;
import com.example.tutor.DTO.RegisterReqtDTO;
import com.example.tutor.DTO.AuthRsponDTO;
import com.example.tutor.Entity.Role;
import com.example.tutor.Entity.User;
import com.example.tutor.Exception.DuplicateResourceException;
import com.example.tutor.Exception.UnauthorizedException;
import com.example.tutor.Repository.RoleRepo;
import com.example.tutor.Repository.UserRepo;
import com.example.tutor.Security.JWT.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthRsponDTO authenticate(LoginReqtDTO request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch(AuthenticationException e){
            throw new UnauthorizedException("Tên đăng nhập hoặc mật khẩu không chính xác!");
        }
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);
        return AuthRsponDTO.builder()
                .token(jwtToken)
                .build();
    }

    public String register(RegisterReqtDTO request){
        if (userRepo.findByUsername(request.getUsername()).isPresent()){
            throw new DuplicateResourceException("Tên đăng nhập tồn tại!");
        }

        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseThrow(()->new RuntimeException("Lỗi:Không thấy quyền ROLE_USER trong Database "));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepo.save(newUser);
        return "Đăng kí thành công";
    }
}
