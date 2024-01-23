package pro.walkin.purifier.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.jcip.annotations.ThreadSafe;

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


        PurifierRequestHolder.setRequest(new JakartaRequest(request));
        PurifierResponseHolder.setResponse(new JakartaResponse((HttpServletResponse) response));

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
