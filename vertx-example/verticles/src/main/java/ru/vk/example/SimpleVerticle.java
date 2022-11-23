package ru.vk.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

@SuppressWarnings({"MagicNumber", "UseOfSystemOutOrSystemErr", "NotNullNullableValidation"})
public final class SimpleVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    vertx.executeBlocking(promise -> {
      System.out.println("Call some blocking API");
      promise.complete();
    }, res -> {
      System.out.println("Start Simple");
      startPromise.complete();
    });
  }
}
