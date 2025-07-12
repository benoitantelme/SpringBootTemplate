package com.template.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  public static final String HOME = "home";

  @GetMapping("/")
  public String base() {
    return HOME;
  }

  @GetMapping("/home")
  public String home() {
    return HOME;
  }
}
