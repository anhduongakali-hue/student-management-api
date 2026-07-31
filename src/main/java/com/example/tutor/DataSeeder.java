package com.example.tutor;

import com.example.tutor.Entity.Role;
import com.example.tutor.Entity.User;
import com.example.tutor.Repository.RoleRepo;
import com.example.tutor.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final PathPatternRequestMatcher.Builder builder;

    @Override
    public void run(String...arg) throws Exception{
        if(roleRepo.count() == 0){
            Role adminRole = Role.builder().name("ROLE_ADMIN").build();
            Role userRole = Role.builder().name("ROLE_USER").build();

            roleRepo.save(adminRole);
            roleRepo.save(userRole);

            System.out.println("Tạo role mặc định trong ĐB thành công ");
        }

        if (userRepo.findByUsername("admin").isEmpty()){
            roleRepo.findByName("ROLE_ADMIN").ifPresent(role -> {
                Set<Role> roles = new HashSet<>();
                roles.add(role);

                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("akalitt7"))
                        .roles(roles)
                        .build();
                userRepo.save(admin);
                System.out.println("Tạo thành công tải khoản : admin / akalitt7");
            });
        }
        if (userRepo.findByUsername("user").isEmpty()){
            roleRepo.findByName("ROLE_USER").ifPresent(role -> {
                Set<Role> roles = new HashSet<>();
                roles.add(role);

                User student = User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("akalitt6"))
                        .roles(roles)
                        .build();
                userRepo.save(student);
                System.out.println("Tạo thành công tài khoản : user / akalitt6");
            });
        }
    }
}
