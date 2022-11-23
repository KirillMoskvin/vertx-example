package ru.vk.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.impl.JavaVerticleFactory;

import java.util.concurrent.Callable;

@SuppressWarnings({"MagicNumber", "UseOfSystemOutOrSystemErr"})
public final class VerticleWithFactory extends AbstractVerticle {
  private final long number;

  private VerticleWithFactory(long number) {
    this.number = number;
  }

  @Override
  public void start() {
    System.out.println("Start " + number + " VerticleWithFactory");
  }

  @SuppressWarnings("NotNullNullableValidation")
  public static final class Factory extends JavaVerticleFactory {
    private long verticleNumber;

    @Override
    public String prefix() {
      return "sphere";
    }

    @SuppressWarnings("ProhibitedExceptionDeclared")
    @Override
    public void createVerticle(String verticleName,
                               ClassLoader classLoader,
                               Promise<Callable<Verticle>> promise) {
      promise.complete(() -> new VerticleWithFactory(verticleNumber++));
    }
  }
}