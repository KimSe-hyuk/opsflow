package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Job;
import com.example.demo.repository.JobRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class JobWorker {

    private final JobRepository jobRepository;
    @Async
    @Transactional 
    public void process(Long jobId) {
        Job job =  jobRepository.findById(jobId).orElseThrow(()-> new EntityNotFoundException("Job not Found"));
            job.setStatus("RUNNING");

        System.out.println(
            "worker thread = " + Thread.currentThread().getName()
        );

        job.setStatus("SUCCESS");
    }
}
