package com.template.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SlowTaskService {

  Logger logger = LoggerFactory.getLogger(SlowTaskService.class);

  @Async
  public void processAsynchronously() {
    slowProcess();
    logger.info("Processing done in the background!");
  }

  private void slowProcess() {
    try {
      Thread.sleep(5000); // Simulates a 5-second delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
