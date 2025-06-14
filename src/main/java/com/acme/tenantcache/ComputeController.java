package com.acme.tenantcache;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compute/{in}")
public class ComputeController {

  private final ComputeService computeService;

  public ComputeController(ComputeService computeService) {
    this.computeService = computeService;
  }

  @GetMapping
  public String compute(@PathVariable String in) {
    return computeService.compute(in);
  }

  @DeleteMapping
  public void discard(@PathVariable String in) {
    computeService.discard(in);
  }

}
