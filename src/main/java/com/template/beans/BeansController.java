package com.template.beans;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeansController {

  Logger logger = LoggerFactory.getLogger(BeansController.class);

  @Autowired private ApplicationContext applicationContext;

  @GetMapping("/beans")
  public String[] index() {
    logger.info("Let's inspect the beans provided by Spring Boot:");

    String[] beanNames = applicationContext.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      logger.info(beanName);
    }
    return beanNames;
  }
}
