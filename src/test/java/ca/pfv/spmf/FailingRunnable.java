package ca.pfv.spmf;

@FunctionalInterface
public interface FailingRunnable {
    void run() throws Exception;
}
