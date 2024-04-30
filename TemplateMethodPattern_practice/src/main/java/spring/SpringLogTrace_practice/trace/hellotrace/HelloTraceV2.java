package spring.SpringLogTrace_practice.trace.hellotrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.SpringLogTrace_practice.trace.TraceId;
import spring.SpringLogTrace_practice.trace.TraceStatus;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    //시작시 호출
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getTraceId(), addSpace(START_PREFIX,
                traceId.getTraceLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    //V2에서 추가
    public TraceStatus beginSync(TraceId beforeTraceId,String message) {
        // 기존 traceId 는 유지하면서 깊이(depth)만 증가시킨다.
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.getTraceId(), addSpace(START_PREFIX,
                nextId.getTraceLevel()), message);
        return new TraceStatus(nextId, startTimeMs, message);
    }
    //종료시 호출
    public void end(TraceStatus status) {
        complete(status, null);
    }
    //예외 시 호출
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getTraceId(),
                    addSpace(COMPLETE_PREFIX, traceId.getTraceLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getTraceId(),
                    addSpace(EX_PREFIX, traceId.getTraceLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        } }

    //level=0
    //level=1 |-->
    //level=2 |   |-->
    //level=2 ex |   |<X-
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
