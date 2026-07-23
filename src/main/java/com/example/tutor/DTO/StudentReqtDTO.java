package com.example.tutor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentReqtDTO {
    @NotBlank(message = "không bỏ trống hoặc khoảng trắng tên!")
    @Size(min = 2, max = 50, message = "tên từ 2-50 kí tự")
    private String name;

    @NotBlank(message = "không bỏ trống ngành học")
    private String major;
}
