package rsp.page;

public class Timer {

    public final Object key;
    private final Runnable cancellation;

    public Timer(Object key, Runnable cancellation) {
        this.key = key;
        this.cancellation = cancellation;
    }

    public void cancel() {
        cancellation.run();
    }
}
