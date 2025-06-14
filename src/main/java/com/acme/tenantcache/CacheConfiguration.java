package com.acme.tenantcache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

  @Bean(destroyMethod = "shutdown")
  public net.sf.ehcache.CacheManager ehCacheManager() {
    return net.sf.ehcache.CacheManager.create();
  }

  @Bean
  public CacheManager cacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
    return new EhCacheCacheManager(ehCacheManager) {

      @Override
      protected Cache getMissingCache(String name) {
        return name.endsWith(ComputeCacheResolver.CACHE_NAME_SUFFIX)
            ? new EhCacheCache(createComputeCache(name, ehCacheManager))
            : super.getMissingCache(name);
      }

    };
  }

  private net.sf.ehcache.Ehcache createComputeCache(String name, net.sf.ehcache.CacheManager ehCacheManager) {
    net.sf.ehcache.Ehcache cache = new net.sf.ehcache.Cache(computeCacheConfiguration(name));
    cache.setCacheManager(ehCacheManager);
    cache.initialise();
    return cache;
  }

  private net.sf.ehcache.config.CacheConfiguration computeCacheConfiguration(String name) {
    return new net.sf.ehcache.config.CacheConfiguration().name(name)
        .maxEntriesLocalHeap(100).timeToLiveSeconds(600).eternal(false);
  }

}
