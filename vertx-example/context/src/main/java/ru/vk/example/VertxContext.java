package ru.vk.example;

import io.vertx.core.Context;
import io.vertx.core.Vertx;

@SuppressWarnings("ALL")
public class VertxContext {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    Context context = vertx.getOrCreateContext();

    context.runOnContext((v) -> {
      System.out.println("This will be executed asynchronously in the same context");
    });


    if (context.isEventLoopContext()) {
      System.out.println("Context attached to Event Loop");
    } else if (context.isWorkerContext()) {
      System.out.println("Context attached to Worker Thread");
    } else if (!Context.isOnVertxThread()) {
      System.out.println("Context not attached to a thread managed by vert.x");
    }

    context.put("data", "hello");
    context.runOnContext((v) -> {
      String hello = context.get("data");
      System.out.println("context data " + hello);
    });
  }
}
