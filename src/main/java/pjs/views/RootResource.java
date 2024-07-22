package pjs.views;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RootResource {

    @Inject
    @Location("index.html")
    Template indexPageTemplate;

    @Route(path = "/", methods = Route.HttpMethod.GET)
    public String root(RoutingContext rc)  {
        // validate login code and return HTTP status code bad or good with session id
        return indexPageTemplate.render();
    }

}
