package pro.walkin.purifier.web;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet filter that sets the request on the {@link PurifierRequestHolder}.
 */
@ThreadSafe
public class PurifierRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            filterChain.doFilter(request, response);
            return;
        }


        PurifierRequestHolder.setRequest(new JavaxRequest(request));
        PurifierResponseHolder.setResponse(new JavaxResponse((HttpServletResponse) response));

        try {
            filterChain.doFilter(request, response);
        } finally {
            PurifierRequestHolder.removeRequest();
            PurifierResponseHolder.removeResponse();
        }
    }

    @Override
    public void destroy() {
    }

}
