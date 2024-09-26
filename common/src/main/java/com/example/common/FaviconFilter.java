package com.example.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FaviconFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        String content = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        String contextPath = request.getContextPath();
        String faviconLink = String.format("<link rel=\"icon\" type=\"image/x-icon\" href=\"%s/favicon.ico\">", contextPath);

        content = content.replaceFirst("(<head[^>]*>)", "$1" + faviconLink);

        response.setContentLength(content.length());
        response.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !path.endsWith(".html") && !path.endsWith(".jsp");
    }
}