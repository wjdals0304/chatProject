package chat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("CharacterEncodingFilter init()");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("CharacterEncodingFilter doFilter()");
		// 요청정보를 가지고 필터를 먼저 호출하고, 수행이 완료되면, url에 맞는 서블렛을 호출한다.
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		// resp.setContentType("application/pdf");
		resp.setCharacterEncoding("UTF-8");

		// 이 구간에서 정보 읽어들여서 session에 저장.
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {

		System.out.println("CharacterEncodingFilter destroy()");

	}

}
