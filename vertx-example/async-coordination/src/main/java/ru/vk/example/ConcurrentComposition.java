package ru.vk.example;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

@SuppressWarnings({"NotNullNullableValidation"})
public final class ConcurrentComposition {

  public static void main(String[] args) {
    Future<Void> future1 = Future.future(promise -> {
      sleep(2000);
      System.out.println("future1");
      promise.complete();
    });

    Future<Void> future2 = Future.future(promise -> {
      sleep(1000);
      System.out.println("future2");
      promise.complete();
    });

    Promise<Void> promise = Promise.promise();
    Runnable asyncFunc = () -> {
      System.out.println("future3");
      promise.complete();
    };
    Future<Void> future3 = promise.future();


    CompositeFuture.all(future1, future2, future3).onComplete(ar -> {
      if (ar.succeeded()) {
        System.out.println("Success ALL");
      } else {
        System.out.println("Fail ALL");
      }
    });

    System.out.println("Running async action...");
    asyncFunc.run();
  }

  private static void sleep(int timeoutMillis) {
    try {
      Thread.sleep(timeoutMillis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
