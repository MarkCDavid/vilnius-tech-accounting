package vilnius.tech.utils;

public class Suspender implements AutoCloseable {

    public Suspender suspend() {
        suspended = true;
        return this;
    }
    @Override
    public void close() {
        suspended = false;
    }

    public boolean isSuspended() {
        return suspended;
    }

    private boolean suspended = false;

}
