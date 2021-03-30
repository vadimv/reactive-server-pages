package rsp.dsl;

import rsp.page.PageRenderContext;

/**
 * The void definition, without any representation in the result DOM tree.
 */
final class EmptyDefinition<S> implements DocumentPartDefinition<S> {
    /**
     * The default instance for reuse.
     */
    public final static EmptyDefinition INSTANCE = new EmptyDefinition();

    /**
     * Creates a new instance of an empty definition.
     */
    public EmptyDefinition() {
        super();
    }

    @Override
    public void accept(PageRenderContext renderContext) {
        // no-op
    }
}
