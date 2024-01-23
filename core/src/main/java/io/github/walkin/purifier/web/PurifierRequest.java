package io.github.walkin.purifier.web;

/**
 * 请求包装类
 */
public interface PurifierRequest {

    <T> T unwrap(Class<T> cls);


    Object getAttribute(String requestKey);

    void setAttribute(String key, Object value);

    String getParameter(String filterParam);
}
