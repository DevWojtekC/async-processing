package pl.hditsystems.sandboxes.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AsyncProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncProcessingApplication.class, args);
		log.info("Initialized :]");
	}
}
