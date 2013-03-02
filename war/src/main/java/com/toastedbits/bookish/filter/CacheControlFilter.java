package com.toastedbits.bookish.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//TODO: remove this filter, useful in development to avoid having to deal with caching headaches
public class CacheControlFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
		resp.setHeader("Last-Modified", new Date().toString());
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		resp.setHeader("Pragma", "no-cache");
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}