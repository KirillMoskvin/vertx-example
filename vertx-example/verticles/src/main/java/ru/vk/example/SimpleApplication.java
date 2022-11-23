package ru.vk.example;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@SuppressWarnings("NotNullNullableValidation")
public enum SimpleApplication {
  ;

  public static void main(String[] args) {
    final var vertx = Vertx.vertx();
    final JsonObject config = new JsonObject().put("name", "simple");
    final DeploymentOptions options = new DeploymentOptions().setWorker(true).setConfig(config);
    vertx.deployVerticle(new SimpleVerticle(), options);
  }
}
