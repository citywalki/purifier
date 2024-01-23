package pro.walkin.purifier.web;

import jakarta.servlet.ServletRequest;

public class JakartaRequest implements PurifierRequest {
    private final ServletRequest servletRequest;

    public JakartaRequest(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> cls) {
        if (cls.isInstance(servletRequest)) {
            return (T) servletRequest;
        }
        throw new RuntimeException("Could not unwrap this [" + toString() + "] as requested Java type [" + cls.getName() + "]");
    }

    @Override
    public Object getAttribute(String requestKey) {
        return servletRequest.getAttribute(requestKey);
    }

    @Override
    public void setAttribute(String key, Object value) {
        servletRequest.setAttribute(key, value);
    }

    @Override
    public String getParameter(String paramKey) {
        return servletRequest.getParameter(paramKey);
    }
}
