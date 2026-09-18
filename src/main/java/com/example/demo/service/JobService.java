package com.example.demo.service;

import com.example.demo.dto.JobResponseDto;
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
    private final int MAX_RETRY = 3;
    private final int START_RETRY = 0;
    private final JobRepository jobRepository;
    private final JobWorker jobWorker;

    public void startJob(Long jobId) {
        jobWorker.process(jobId);
    }
    
    public Job findById(Long id) {
        return jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
    } 
    @Transactional 
    public void recordFailure(Long id){
        Job job = jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
     if (job.getRetryCount() >= MAX_RETRY) {
        return;
    }

    job.setRetryCount(job.getRetryCount() + 1);

    if (job.getRetryCount() >= MAX_RETRY) {
        job.setStatus("FAILED");
    }
            
         
    }
    @Transactional
    public JobResponseDto saveJob(JobStatusPostRequestDto request) {

        Job job = jobRepository.save(
                new Job(request.getName(), request.getStatus(),START_RETRY)
        );

        return new JobResponseDto(
                job.getId(),
                job.getName(),
                job.getStatus()
        );
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
