package rsp.examples.crud.components;

import rsp.Component;
import rsp.dsl.DocumentPartDefinition;
import rsp.state.UseState;

import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

import static rsp.dsl.Html.*;
import static rsp.state.UseState.useState;

public class Create<T> implements Component<DetailsViewState<T>> {

    private final Function<Consumer<T>, Form> formFunction;
    public Create(Function<Consumer<T>, Form> formFunction) {
        this.formFunction = formFunction;
    }


    @Override
    public DocumentPartDefinition render(UseState<DetailsViewState<T>> us) {
        return div(span("Create"),
                   formFunction.apply(useState(() -> us.get().currentValue.get(),
                                            v -> us.accept(us.get().withValue(v).withValidationErrors(Collections.EMPTY_MAP))))
                                                   .render(useState(() -> new Form.State(us.get().visible, us.get().validationErrors),
                                                                     v -> us.accept(v.visible ? us.get().withValidationErrors(v.validationErrors) : us.get().hide()))));
    }


}
