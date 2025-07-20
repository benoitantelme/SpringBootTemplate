package com.template.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowTaskController {

  public static final String PROCESSING_STARTED = "Processing Started!";
  @Autowired SlowTaskService slowTaskService;

  @GetMapping("/slow")
  public String slowTaskAsync() {
    slowTaskService.processAsynchronously();
    return PROCESSING_STARTED;
  }
}
