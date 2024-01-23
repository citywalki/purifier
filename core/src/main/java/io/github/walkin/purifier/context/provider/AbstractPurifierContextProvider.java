package io.github.walkin.purifier.context.provider;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import io.github.walkin.purifier.context.LazyPurifierContext;
import io.github.walkin.purifier.context.PurifierContext;
import io.github.walkin.purifier.parser.PurifierParser;

/**
 * Base implemention of a provider that implements base functionality.
 */
public abstract class AbstractPurifierContextProvider implements PurifierContextProvider {

    private final PurifierParser parser;

    public AbstractPurifierContextProvider() {
        this(new PurifierParser());
    }

    public AbstractPurifierContextProvider(PurifierParser parser) {
        this.parser = parser;
    }

    @Override
    public PurifierContext getContext(Class beanClass) {
        return new LazyPurifierContext(beanClass, parser, getFilter(beanClass));
    }

    @Override
    public boolean isFilteringEnabled() {
        return true;
    }

    /**
     * Get the filter expression.
     *
     * @param beanClass class of the top-level bean being filtered
     * @return filter expression
     */
    protected abstract String getFilter(Class beanClass);


    @Override
    public void serializeAsIncludedField(Object pojo, JsonGenerator jgen, SerializerProvider provider,
                                         PropertyWriter writer) throws Exception {
        writer.serializeAsField(pojo, jgen, provider);
    }

    @Override
    public void serializeAsExcludedField(Object pojo, JsonGenerator jgen, SerializerProvider provider,
                                         PropertyWriter writer) throws Exception {
        writer.serializeAsOmittedField(pojo, jgen, provider);
    }

}
