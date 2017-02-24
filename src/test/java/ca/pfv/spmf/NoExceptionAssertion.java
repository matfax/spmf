package ca.pfv.spmf;

/**
 * Creates an assertion that no exception is thrown.
 */
public enum NoExceptionAssertion {
    ;
    public static void assertDoesNotThrow(FailingRunnable action) {
        try {
            action.run();
        } catch (Exception ex) {
            throw new Error("expected action not to throw, but it did!", ex);
        }
    }
}
