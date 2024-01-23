package io.github.walkin.purifier.web;

import com.google.common.base.MoreObjects;
import io.github.walkin.purifier.context.provider.AbstractPurifierContextProvider;
import io.github.walkin.purifier.name.AnyDeepName;
import io.github.walkin.purifier.parser.PurifierParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom context provider that gets the filter expression from the request.
 */
public class RequestPurifierContextProvider extends AbstractPurifierContextProvider {

    private final String defaultFilter;

    private String filterParam;

    public RequestPurifierContextProvider() {
        this("fields", null);
    }

    public RequestPurifierContextProvider(String filterParam, String defaultFilter) {
        this(new PurifierParser(), filterParam, defaultFilter);

    }

    public RequestPurifierContextProvider(PurifierParser parser, String filterParam, String defaultFilter) {
        super(parser);
        this.filterParam = filterParam;
        this.defaultFilter = defaultFilter;
    }

    @Override
    protected String getFilter(Class beanClass) {
        PurifierRequest request = getRequest();

        FilterCache cache = FilterCache.getOrCreate(request);
        String filter = cache.get(beanClass);

        if (filter == null) {
            filter = MoreObjects.firstNonNull(getFilter(request), defaultFilter);
            filter = customizeFilter(filter, request, beanClass);
            cache.put(beanClass, filter);
        }

        return filter;
    }

    @Override
    public boolean isFilteringEnabled() {
        PurifierRequest request = getRequest();

        if (request == null) {
            return false;
        }

        PurifierResponse response = getResponse();

        if (response == null) {
            return false;
        }

        return isFilteringEnabled(request, response);
    }

    protected boolean isFilteringEnabled(PurifierRequest request, PurifierResponse response) {
        int status = getResponseStatusCode(request, response);

        if (!isSuccessStatusCode(status)) {
            return false;
        }

        String filter = getFilter(request);

        if (AnyDeepName.ID.equals(filter)) {
            return false;
        }

        if (filter != null) {
            return true;
        }

        if (AnyDeepName.ID.equals(defaultFilter)) {
            return false;
        }

        return defaultFilter != null;
    }

    protected int getResponseStatusCode(PurifierRequest request, PurifierResponse response) {
        return response.getStatus();
    }

    protected boolean isSuccessStatusCode(int status) {
        return status >= PurifierResponse.SC_OK && status < PurifierResponse.SC_MULTIPLE_CHOICES;
    }

    protected String getFilter(PurifierRequest request) {
        return request.getParameter(filterParam);
    }

    protected PurifierRequest getRequest() {
        return PurifierRequestHolder.getRequest();
    }

    protected PurifierResponse getResponse() {
        return PurifierResponseHolder.getResponse();
    }

    protected String customizeFilter(String filter, PurifierRequest request, Class beanClass) {
        return customizeFilter(filter, beanClass);
    }

    protected String customizeFilter(String filter, Class beanClass) {
        return filter;
    }

    private static class FilterCache {
        public static final String REQUEST_KEY = FilterCache.class.getName();

        @SuppressWarnings("RedundantStringConstructorCall")
        private static final String NULL = new String();

        private final Map<Class, String> map = new HashMap<>();

        public static FilterCache getOrCreate(PurifierRequest request) {
            FilterCache cache = (FilterCache) request.getAttribute(REQUEST_KEY);

            if (cache == null) {
                cache = new FilterCache();
                request.setAttribute(REQUEST_KEY, cache);
            }

            return cache;
        }

        @SuppressWarnings("StringEquality")
        public String get(Class key) {
            String value = map.get(key);

            if (value == NULL) {
                value = null;
            }

            return value;
        }

        public void put(Class key, String value) {
            if (value == null) {
                value = NULL;
            }

            map.put(key, value);
        }

        public void remove(Class key) {
            map.remove(key);
        }

        public void clear() {
            map.clear();
        }

    }

}
