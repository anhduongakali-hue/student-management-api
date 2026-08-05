package com.example.tutor.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqtDTO {
    @NotBlank(message = "Không được để trống tên đăng nhập")
    private String username;

    @NotBlank(message = "Không được để trống mật khẩu")
    private String password;
}
