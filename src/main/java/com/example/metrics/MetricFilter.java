package com.example.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WorkIt on 25/05/2016.
 */
@Component
public class MetricFilter implements Filter {
    @Autowired
    private IMetricService metricService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(metricService == null){
            metricService = (IMetricService) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(filterConfig.getServletContext())
                    .getBean("metricService");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);
        final String req = httpServletRequest.getMethod()+" "+httpServletRequest.getRequestURI();

        filterChain.doFilter(servletRequest,servletResponse);

        final int status = ((HttpServletResponse)servletResponse).getStatus();
        metricService.increaseCount(req,status);
    }

    @Override
    public void destroy() {

    }
}
