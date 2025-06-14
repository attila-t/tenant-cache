package com.acme.tenantcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ComputeService {

  private static final Logger logger = LoggerFactory.getLogger(ComputeService.class);

  @Cacheable(cacheResolver = "computeCacheResolver", key = "#in")
  public String compute(String in) {
    String out = new StringBuilder(in).reverse().toString();
    logger.info("Computed '{}'", out);
    return out;
  }

  @CacheEvict(cacheResolver = "computeCacheResolver", key = "#in")
  public void discard(String in) {
    logger.info("Discarded '{}'", in);
  }

}
