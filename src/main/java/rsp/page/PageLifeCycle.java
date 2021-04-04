package rsp.page;

/**
 * The listener interface for receiving page lifecycle events.
 * @param <S> page state type, an immutable class
 */
public interface PageLifeCycle<S> {

    /**
     * Invoked before an live page session created.
     * @param sid the qualified session Id of the page created
     * @param state the current state
     */
    void beforeLivePageCreated(QualifiedSessionId sid, S state);

    /**
     * Invoked after an live page session closed.
     * @param sid the qualified session Id of the page being closed
     * @param state the current state
     */
    void afterLivePageClosed(QualifiedSessionId sid, S state);

    /**
     * The default lifecycle listener implementation doing nothing.
     * @param <S> page state type, an immutable class
     */
    class Default<S> implements PageLifeCycle<S> {

        @Override
        public void beforeLivePageCreated(QualifiedSessionId sid, S state) {
            //no-op
        }

        @Override
        public void afterLivePageClosed(QualifiedSessionId sid, S state) {
            //no-op
        }
    }
}
