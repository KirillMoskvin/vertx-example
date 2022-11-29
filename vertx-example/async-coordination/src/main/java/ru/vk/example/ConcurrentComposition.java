package ru.vk.example;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

@SuppressWarnings({"NotNullNullableValidation"})
public final class ConcurrentComposition {

  public static void main(String[] args) {
    Future<String> future1 = Future.future(promise -> {
      sleep(2000);
      System.out.println("future1");
      promise.complete("Never");
    });

    Future<String> future2 = Future.future(promise -> {
      sleep(1000);
      System.out.println("future2");
      promise.complete(" gonna");
    });

    Promise<String> promise = Promise.promise();
    Runnable asyncFunc = () -> {
      System.out.println("future3");
      promise.complete(" give you up");
    };
    Future<String> future3 = promise.future();


    CompositeFuture.all(future1, future2, future3).onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        CompositeFuture result = asyncResult.result();
        System.out.println("Success ALL " + result.resultAt(0) + result.resultAt(1) + result.resultAt(2));
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
