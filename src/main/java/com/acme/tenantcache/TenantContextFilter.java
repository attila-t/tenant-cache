package com.acme.tenantcache;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TenantContextFilter implements Filter {

  @Autowired
  private TenantContext tenantContext;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      String tenantId = ((HttpServletRequest) request).getHeader("tenant-id");
      if (tenantId == null) {
        tenantId = "default";
      }

      tenantContext.setTenantId(tenantId);

      chain.doFilter(request, response);

    } finally {
      tenantContext.clear();
    }
  }

}
