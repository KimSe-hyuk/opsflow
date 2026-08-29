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
    
    public Job findById(Long id) {
        return jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
    } 

    @Transactional
    public void saveJob(JobStatusPostRequestDto requestDto) {
        jobRepository.save(new Job(requestDto.getName(), requestDto.getStatus()));
    }

  

    @Transactional
    public Job updateStatus(Long id,JobStatusUpdateRequestDto requestDto) {
        Job job = jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
        job.setStatus(requestDto.getStatus());
        return job;
    }
    @Transactional
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
        jobRepository.delete(job);
       
    }
}
