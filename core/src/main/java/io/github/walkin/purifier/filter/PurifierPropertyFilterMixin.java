package io.github.walkin.purifier.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import net.jcip.annotations.ThreadSafe;

/**
 * Jackson mixin that register the filter id for the @{@link PurifierPropertyFilter}.
 */
@ThreadSafe
@JsonFilter(PurifierPropertyFilter.FILTER_ID)
public class PurifierPropertyFilterMixin {
}
