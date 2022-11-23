package ru.vk.example;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

@SuppressWarnings("NotNullNullableValidation")
public final class Client {

  public static void main(String[] args) {
    WebClient client = WebClient.create(Vertx.vertx());
    client
        .get(9090, "localhost", "/api/data/12345")
        .send(ar -> {
          if (ar.succeeded()) {
            var response = ar.result();
            System.out.println("Received response with status code " + response.statusCode());
            System.out.println("Body message: " + response.bodyAsString());
          } else {
            System.out.println("Something went wrong " + ar.cause().getMessage());
          }
        });
  }
}
