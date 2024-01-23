package io.github.walkin.purifier.context.provider;

import io.github.walkin.purifier.name.AnyDeepName;
import io.github.walkin.purifier.parser.PurifierParser;
import net.jcip.annotations.ThreadSafe;

/**
 * Provider implementation that just takes a fixed filter expression.
 */
@ThreadSafe
public class SimplePurifierContextProvider extends AbstractPurifierContextProvider {

    private final String filter;

    public SimplePurifierContextProvider(PurifierParser parser, String filter) {
        super(parser);
        this.filter = filter;
    }

    @Override
    public boolean isFilteringEnabled() {
        return filter != null && !AnyDeepName.ID.equals(filter);
    }

    @Override
    protected String getFilter(Class beanClass) {
        return filter;
    }

}
