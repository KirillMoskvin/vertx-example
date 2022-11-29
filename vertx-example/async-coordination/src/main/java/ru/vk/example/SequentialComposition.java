package ru.vk.example;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

@SuppressWarnings("NotNullNullableValidation")
public final class SequentialComposition {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    FileSystem fs = vertx.fileSystem();

    final var fileName = "example1.txt";

    Future<Void> future1 = Future.future(promise -> {
      System.out.println("future1");
      fs.createFile(fileName, promise);
    });

    Future<Void> startFuture = future1
        .compose(result -> Future.<Void>future(promise -> {
          System.out.println("future2");
          fs.writeFile(fileName, Buffer.buffer("Test file write"), promise);
        }))
        .compose(result -> Future.future(promise -> {
          System.out.println("future3");
          fs.move(fileName, "example2.txt", promise);
        }));

    startFuture.onComplete(
        result -> System.out.println("Complete ALL " + (result.succeeded() ? "Success" : result.cause().getMessage()))
    );
    System.out.println("Main finished");
  }
}
