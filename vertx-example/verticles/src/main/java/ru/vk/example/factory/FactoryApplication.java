package ru.vk.example.factory;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

@SuppressWarnings({"UseOfSystemOutOrSystemErr", "NotNullNullableValidation"})
public enum FactoryApplication {
  ;

  public static void main(String[] args) {
    final var vertx = Vertx.vertx();
    final VerticleWithFactory.Factory factory = new VerticleWithFactory.Factory();
    vertx.registerVerticleFactory(factory);
    final DeploymentOptions options = new DeploymentOptions().setInstances(3);
    vertx.deployVerticle(factory.prefix() + ':' + VerticleWithFactory.class.getName(), options, res -> {
      if (res.succeeded()) {
        System.out.println("Verticle deployed with id: " + res.result());
      }
    });
  }
}
