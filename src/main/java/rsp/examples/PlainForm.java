package rsp.examples;

import rsp.App;
import rsp.Component;
import rsp.jetty.JettyServer;
import rsp.server.HttpRequest;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static rsp.dsl.Html.*;

/**
 * An example with plain detached pages:
 * <ul>
 *     <li>a page with an input form</li>
 *     <li>a page with representation of the entered data</li>
 * </ul>
 */
public class PlainForm {
    public static void main(String[] args) {
        final App<Optional<FullName>> app = new App<>(PlainForm::routes,
                                                      pages());
        final JettyServer server = new JettyServer(8080, "", app);
        server.start();
        server.join();
    }

    public static class FullName {
        public final String firstName;
        public final String secondName;

        public FullName(String firstName, String secondName) {
            this.firstName = Objects.requireNonNull(firstName);
            this.secondName = Objects.requireNonNull(secondName);
        }

        public String toString() {
            return firstName + " " + secondName;
        }
    }

    private static CompletableFuture<Optional<FullName>> routes(HttpRequest r) {
        return r.method.equals(HttpRequest.Methods.POST) ?
                CompletableFuture.completedFuture(Optional.of(new FullName(r.param("firstname").orElseThrow(),
                                                                           r.param("lastname").orElseThrow())))
                : CompletableFuture.completedFuture(Optional.empty());
    }

    private static Component<Optional<FullName>> pages() {
        return s -> html(
                        headPlain(title("Plain Form Pages")),
                        body(
                            s.get().isEmpty() ? formComponent().render(s) : formResult().render(s)
                        )
        );
    }

    private static Component<Optional<FullName>> formComponent() {
        return s -> div(
                h2(text("HTML Form")),
                form(attr("action", "page0"), attr("method", "post"),
                label(attr("for", "firstname"), text("First name:")),
                input(attr("type", "text"), attr("name","firstname"), attr("value", "First")),
                br(),
                label(attr("for", "lastname"), text("Last name:")),
                input(attr("type", "text"), attr("name","lastname"), attr("value", "Last")),
                br(),
                input(attr("type", "submit"), attr("value", "Submit"))),
                p("If you click the 'Submit' button, the form-data will be sent to page0."));
    }

    private static Component<Optional<FullName>> formResult() {
        return s -> div(h2(text("HTML Form result")),
                        div(p("The submitted name is " + s.get().orElseThrow())));
    }
}