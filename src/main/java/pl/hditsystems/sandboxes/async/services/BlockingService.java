package pl.hditsystems.sandboxes.async.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * Created by wojtek on 02.02.2017.
 */
@Component
@Slf4j
public class BlockingService {
  private final TaskExecutor executor;
  private Random random = new Random();

  @Autowired
  public BlockingService(TaskExecutor executor) {
    this.executor = executor;
  }

  public String getData() {

    try {
      Thread.sleep(200 + random.nextInt(500));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Simulating blocking operation");
    return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  public CompletableFuture<String> getDataFuture() {
    return CompletableFuture.supplyAsync(this::getData, executor);
  }
}
