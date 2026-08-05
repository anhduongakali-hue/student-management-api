package com.example.tutor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqtDTO {

    @NotBlank(message = "không để trống tên đăng nhập")
    @Size(min = 3,message = "tên đăng nhập ít nhất 3 kí tự")
    @Pattern(regexp = "^\\S+$",message = "tên đăng nhập không được chứa khoảng trắng")
    private String username;

    @NotBlank(message = "không để trống mật khẩu")
    @Size(min = 6,message = "mật khấu ít nhất 6 kí tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).+$",
    message = "mật khẩu phải chứa ít nhất một chữ hoa , 1 chữ thường ,1 số và không có khoảng  trắng")
    private String password;
}
