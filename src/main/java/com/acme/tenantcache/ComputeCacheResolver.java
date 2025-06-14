package com.acme.tenantcache;

import java.util.Collection;
import java.util.Set;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.stereotype.Component;

@Component
public class ComputeCacheResolver extends SimpleCacheResolver {

  public static final String CACHE_NAME_SUFFIX = ".computeCache";

  private final TenantContext tenantContext;

  public ComputeCacheResolver(CacheManager cacheManager, TenantContext tenantContext) {
    super(cacheManager);
    this.tenantContext = tenantContext;
  }

  @Override
  public Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
    String cacheName = tenantContext.getTenantId() + CACHE_NAME_SUFFIX;
    return Set.of(cacheName);
  }

}
