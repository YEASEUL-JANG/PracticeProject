package spring.SpringLogTrace_practice.trace.logtrace;

import spring.SpringLogTrace_practice.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end (TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
