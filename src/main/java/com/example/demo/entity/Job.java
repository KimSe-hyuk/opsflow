package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;
   @Column(name = "retry_count", nullable = false)
    private int  retryCount = 0;
    public Job(String name, String status, int retryCount) {
        this.name = name;
        this.status = status;
        this.retryCount = retryCount;
    }
 
   

    @ManyToOne
    @JoinColumn(name = "job_group_id")
    private JobGroup jobGroup;
}
