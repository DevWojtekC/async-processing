package pl.hditsystems.sandboxes.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.hditsystems.sandboxes.async.services.BlockingService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by wojtek on 02.02.2017.
 */
@Component
@Slf4j
public class ExperimentRunner implements ApplicationRunner {
  private final BlockingService blockingService;

  @Autowired
  public ExperimentRunner(BlockingService blockingService) {
    this.blockingService = blockingService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("Running experiment...");

    List<CompletableFuture<String>> dataFutures = IntStream.range(0, 20).mapToObj(value -> blockingService.getDataFuture())
        .collect(Collectors.toList());

    List<String> loadedData = foldingJoin(dataFutures).join();

    log.info("Loaded: {}", loadedData);
  }

  private static <T> CompletableFuture<List<T>> foldingJoin(List<CompletableFuture<T>> futures) {
    CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    return allDoneFuture.thenApply(v ->
        futures.stream().
            map(future -> future.join()).
            collect(Collectors.<T>toList())
    );
  }
}
