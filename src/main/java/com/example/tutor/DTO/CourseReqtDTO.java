package com.example.tutor.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseReqtDTO {
    @NotBlank(message = "không để trống khóa học")
    private String name;
}
