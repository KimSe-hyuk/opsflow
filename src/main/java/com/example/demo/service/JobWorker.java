package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobWorker {

    @Async
    public void process(Long jobId) {
        System.out.println(
            "worker thread = " + Thread.currentThread().getName()
            + ", jobId = " + jobId
        );
    }
}
