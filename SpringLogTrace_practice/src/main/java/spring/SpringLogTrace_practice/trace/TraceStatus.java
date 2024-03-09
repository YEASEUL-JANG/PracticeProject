package spring.SpringLogTrace_practice.trace;

public class TraceStatus {
    private TraceId traceId;
    //로그 시작시간. 로그 종료시 이 시작시간을 기준으로 시작~종료까지 수행시간을 구할 수 있음.
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
