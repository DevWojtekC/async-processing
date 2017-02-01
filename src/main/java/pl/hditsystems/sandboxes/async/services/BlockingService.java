package pl.hditsystems.sandboxes.async.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Created by wojtek on 02.02.2017.
 */
@Component
@Slf4j
public class BlockingService {
  Random random = new Random();

  public String getData() {
    log.debug("Simulating blocking operation");
    try {
      Thread.sleep(200 + random.nextInt(500));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
