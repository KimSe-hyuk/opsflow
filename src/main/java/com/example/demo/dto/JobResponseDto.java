package com.example.demo.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponseDto {

    private Long id;
    private String name;
    private String status;

    public JobResponseDto(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
