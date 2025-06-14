package com.acme.tenantcache;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

  private static final ThreadLocal<String> tenantIdHolder = new ThreadLocal<>();

  public void setTenantId(String tenantId) {
    tenantIdHolder.set(tenantId);
  }

  public String getTenantId() {
    return tenantIdHolder.get();
  }

  public void clear() {
    tenantIdHolder.remove();
  }

}
