package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
@Getter
@Setter
public class JobStatusPostRequestDto {

    @NotBlank(message = "name은 비어 있을 수 없습니다.")
    private String name;

    @NotBlank(message = "status는 비어 있을 수 없습니다.")
    private String status;
}
