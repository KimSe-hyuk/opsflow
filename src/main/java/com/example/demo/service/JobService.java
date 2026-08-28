package com.example.demo.service;

import com.example.demo.dto.JobStatusPostRequestDto;
import com.example.demo.dto.JobStatusUpdateRequestDto;
import com.example.demo.entity.Job;
import com.example.demo.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobService {

    private final JobRepository jobRepository;
    
    @Transactional
    public Job findById(Long id) {
        return jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
    }

    @Transactional
    public Job saveJob(JobStatusPostRequestDto requestDto) {
        return jobRepository.save(new Job(requestDto.getName(), requestDto.getStatus()));
    }

  

    @Transactional
    public Job updateStatus(JobStatusUpdateRequestDto requestDto) {
        Job job = jobRepository.findById(requestDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + requestDto.getId()));
        job.setStatus(requestDto.getStatus());
        return job;
    }
}
