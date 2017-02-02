package pl.hditsystems.sandboxes.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by wojtek on 02.02.2017.
 */
@Configuration
public class ExecutorConfig {
  @Bean
  public TaskExecutor buildExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(30);
    return executor;
  }
}
