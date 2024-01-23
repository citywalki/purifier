package io.github.walkin.purifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.github.walkin.purifier.context.provider.PurifierContextProvider;
import io.github.walkin.purifier.context.provider.SimplePurifierContextProvider;
import io.github.walkin.purifier.filter.PurifierPropertyFilter;
import io.github.walkin.purifier.filter.PurifierPropertyFilterMixin;
import io.github.walkin.purifier.parser.PurifierParser;

/**
 * Provides various way of registering a {@link PurifierPropertyFilter} with a Jackson ObjectMapper.
 */
public class Purifier {

    private Purifier() {
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a static filter expression.
     *
     * @param mapper the Jackson Object Mapper
     * @param filter the filter expressions
     * @return object mapper, mainly for convenience
     * @throws IllegalStateException if the filter was unable to be registered
     */
    public static ObjectMapper init(ObjectMapper mapper, String filter) throws IllegalStateException {
        return init(mapper, new SimplePurifierContextProvider(new PurifierParser(), filter));
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a static filter expression.
     *
     * @param mappers the Jackson Object Mappers to init
     * @param filter  the filter expressions
     * @throws IllegalStateException if the filter was unable to be registered
     */
    public static void init(Iterable<ObjectMapper> mappers, String filter) throws IllegalStateException {
        init(mappers, new SimplePurifierContextProvider(new PurifierParser(), filter));
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a specific context provider.
     *
     * @param mapper          the Jackson Object Mapper
     * @param contextProvider the context provider to use
     * @return object mapper, mainly for convenience
     * @throws IllegalStateException if the filter was unable to be registered
     */
    public static ObjectMapper init(ObjectMapper mapper,
                                    PurifierContextProvider contextProvider) throws IllegalStateException {
        return init(mapper, new PurifierPropertyFilter(contextProvider));
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a specific context provider.
     *
     * @param mappers         the Jackson Object Mappers to init
     * @param contextProvider the context provider to use
     * @throws IllegalStateException if the filter was unable to be registered
     */
    public static void init(Iterable<ObjectMapper> mappers, PurifierContextProvider contextProvider) {
        init(mappers, new PurifierPropertyFilter(contextProvider));
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a specific property filter.
     *
     * @param mapper the Jackson Object Mapper
     * @param filter the property filter
     * @return object mapper, mainly for convenience
     * @throws IllegalStateException if the filter was unable to be registered
     */
    @SuppressWarnings("deprecation")
    public static ObjectMapper init(ObjectMapper mapper, PurifierPropertyFilter filter) throws IllegalStateException {
        FilterProvider filterProvider = mapper.getSerializationConfig().getFilterProvider();
        SimpleFilterProvider simpleFilterProvider;

        if (filterProvider instanceof SimpleFilterProvider) {
            simpleFilterProvider = (SimpleFilterProvider) filterProvider;
        } else if (filterProvider == null) {
            simpleFilterProvider = new SimpleFilterProvider();
            mapper.setFilters(simpleFilterProvider);
        } else {
            throw new IllegalStateException("Unable to register squiggly filter with FilterProvider of type " +
                                                    filterProvider.getClass().getName() +
                                                    ".  You'll have to register the filter manually");

        }

        simpleFilterProvider.addFilter(PurifierPropertyFilter.FILTER_ID, filter);
        mapper.addMixIn(Object.class, PurifierPropertyFilterMixin.class);

        return mapper;
    }

    /**
     * Initialize a @{@link PurifierPropertyFilter} with a specific property filter.
     *
     * @param mappers the Jackson Object Mappers to init
     * @param filter  the property filter
     * @throws IllegalStateException if the filter was unable to be registered
     */
    public static void init(Iterable<ObjectMapper> mappers, PurifierPropertyFilter filter) {
        for (ObjectMapper mapper : mappers) {
            init(mapper, filter);
        }
    }

}
