package spring.SpringLogTrace_practice.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.SpringLogTrace_practice.trace.TraceId;
import spring.SpringLogTrace_practice.trace.TraceStatus;
@Slf4j
public class FieldLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; //TraceId 를 동기화 (동시성이슈발생)


    private void syncTraceId() {
        if(traceIdHolder == null){
            traceIdHolder = new TraceId();
        }else {
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();//싱크를 하면 traceHolder에 traceId가 보관된다.
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getTraceId(), addSpace(START_PREFIX,
                traceId.getTraceLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);

    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
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
        }
        //http요청이 끝났으므로 보관하고있는 traceId를 비우거나 레벨을 낮춘다
        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdHolder.isFirstLevel()){
            traceIdHolder = null;
        }else{
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
