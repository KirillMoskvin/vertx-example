package ru.vk.example;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

@SuppressWarnings("NotNullNullableValidation")
public final class SequentialComposition {

  public static void main(String[] args) {
    FileSystem fs = Vertx.vertx().fileSystem();

    final var fileName = "example1.txt";

    Future<Void> future1 = Future.future(promise -> {
      System.out.println("future1");
      fs.createFile(fileName, promise);
    });

    Future<Void> startFuture = future1
        .compose(v -> Future.<Void>future(promise -> {
          System.out.println("future2");
          fs.writeFile(fileName, Buffer.buffer(), promise);
        }))
        .compose(v -> Future.future(promise -> {
          System.out.println("future3");
          fs.move(fileName, "example2.txt", promise);
        }));

    startFuture.onComplete(result -> System.out.println("Complete ALL " + result.succeeded()));
  }
}
