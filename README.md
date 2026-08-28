# OpsFlow

대량 작업을 안정적으로 비동기 처리하고,
실패 작업의 재시도·격리·복구 과정과
서비스 상태를 관측할 수 있는
Spring Boot 기반 운영 자동화 플랫폼입니다.

'''
예를 들어 회사에서 CSV 10만 건을 받아 DB에 처리한다고 생각하면 된다.

사용자 CSV/API 요청
        ↓
     Validation
        ↓
      Job 생성
        ↓
       Queue
        ↓
      Worker
        ↓
 ┌──────┴──────┐
 │             │
성공           실패
 │             │
DB 저장      Retry
               ↓
        Exponential Backoff
               ↓
          계속 실패
               ↓
              DLQ
               ↓
           수동 재처리

그리고 전체 과정을 관측한다.

Application
   ├── Log
   ├── Trace ID
   ├── Metric
   └── Error
        ↓
Prometheus
        ↓
Grafana

마지막에 선택적으로:

장애 로그 + Metric
       ↓
     Ollama
       ↓
장애 요약 / 원인 후보 / Runbook 초안
'''