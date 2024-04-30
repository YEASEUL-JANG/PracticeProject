package spring.SpringLogTrace_practice.callback;

public interface TraceCallback<T> {
    T call();
}
