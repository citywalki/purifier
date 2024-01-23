package io.github.walkin.purifier.context;

import io.github.walkin.purifier.parser.PurifierNode;
import io.github.walkin.purifier.parser.PurifierParser;
import net.jcip.annotations.NotThreadSafe;

import java.util.List;

/**
 * Squiggly context that loads the parsed nodes on demand.
 */
@NotThreadSafe
public class LazyPurifierContext implements PurifierContext {

    private final Class beanClass;

    private final String filter;

    private final PurifierParser parser;

    private List<PurifierNode> nodes;

    public LazyPurifierContext(Class beanClass, PurifierParser parser, String filter) {
        this.beanClass = beanClass;
        this.parser = parser;
        this.filter = filter;
    }

    @Override
    public Class getBeanClass() {
        return beanClass;
    }

    @Override
    public List<PurifierNode> getNodes() {
        if (nodes == null) {
            nodes = parser.parse(filter);
        }

        return nodes;
    }

    @Override
    public String getFilter() {
        return filter;
    }

}
