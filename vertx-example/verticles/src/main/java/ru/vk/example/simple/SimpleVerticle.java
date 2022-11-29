package ru.vk.example.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

@SuppressWarnings({"MagicNumber", "UseOfSystemOutOrSystemErr", "NotNullNullableValidation"})
public final class SimpleVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    System.out.println(deploymentID() + " deploying in thread " + Thread.currentThread() + this.context.config());
    vertx.executeBlocking(promise -> {
      System.out.println(deploymentID() + " is calling some blocking API " + Thread.currentThread());
      promise.complete();
    }, res -> {
      System.out.println("Start Simple");
      startPromise.complete();
    });
  }
}
