package ru.vk.example;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@SuppressWarnings("NotNullNullableValidation")
public final class Server {

  public static void main(String[] args) {
    final var vertx = Vertx.vertx();

    Router router = Router.router(vertx);
    router.get("/api/data/:id")
        .handler(Server::handleData);

    vertx.createHttpServer()
        .requestHandler(router)
        .listen(9090,
            result -> {
              if (result.succeeded()) {
                System.out.println("Server start successful");
              } else {
                System.out.println("Server start failed");
              }
            }
        );
  }

  private static void handleData(RoutingContext routingContext) {
    String id = routingContext.request()
        .getParam("id");

    routingContext.response()
        .putHeader("content-type", "text/plain")
        .setStatusCode(200)
        .end("Hello vertx: " + id);
  }
}
