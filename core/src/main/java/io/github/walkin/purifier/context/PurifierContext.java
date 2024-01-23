package io.github.walkin.purifier.context;

import io.github.walkin.purifier.parser.PurifierNode;

import java.util.List;

/**
 * A squiggly context provides parsing and filtering information to the
 * {@link SquigglyPropertyFilter}.  Contexts are usually not thread safe.
 */
public interface PurifierContext {

    /**
     * Get the top-level bean class being filtered.
     *
     * @return bean class
     */
    Class getBeanClass();

    /**
     * Get the parsed nodes.
     *
     * @return nodes
     */
    List<PurifierNode> getNodes();

    /**
     * Get the filter expression.
     *
     * @return filter expression
     */
    String getFilter();

}
