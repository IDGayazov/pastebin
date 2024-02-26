package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/user")
public class AccessFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		Object user = ((HttpServletRequest)request).getSession().getAttribute("user");	
		if(user == null) {
			((HttpServletResponse) response).sendRedirect("/pastebinApp/login");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
