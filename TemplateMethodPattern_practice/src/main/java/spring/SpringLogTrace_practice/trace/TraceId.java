package spring.SpringLogTrace_practice.trace;

import java.util.UUID;

public class TraceId {

    private String traceId;
    private int traceLevel;

    public TraceId() {
        this.traceId = createId();
        this.traceLevel = 0;
    }

    private TraceId(String traceId, int traceLevel) {
        this.traceId = traceId;
        this.traceLevel = traceLevel;
    }

    private String createId(){
        return UUID.randomUUID().toString().substring(0,8);
    }
    //단계가 진행됨에 따라 레벨(Depth)가 증가됨
    public TraceId createNextId(){
        return new TraceId(traceId,traceLevel+1);
    }
    //반환이 진행됨에 따라 레벨(Depth)가 감소됨
    public TraceId createPreviousId(){
        return new TraceId(traceId,traceLevel-1);
    }
    public boolean isFirstLevel(){
        return traceLevel == 0;
    }

    public String getTraceId() {
        return traceId;
    }

    public int getTraceLevel() {
        return traceLevel;
    }
}
