package io.github.walkin.purifier.metric;

import com.google.common.collect.Maps;
import io.github.walkin.purifier.bean.BeanInfoIntrospector;
import io.github.walkin.purifier.filter.PurifierPropertyFilter;
import io.github.walkin.purifier.metric.source.CompositePurifierMetricsSource;
import io.github.walkin.purifier.metric.source.PurifierMetricsSource;
import io.github.walkin.purifier.parser.PurifierParser;
import net.jcip.annotations.ThreadSafe;

import java.util.SortedMap;

/**
 * Provides API for obtaining various metrics in the squiggly libraries, such as cache statistics.
 */
@ThreadSafe
public class PurifierMetrics {

    private static final PurifierMetricsSource METRICS_SOURCE;

    static {
        METRICS_SOURCE = new CompositePurifierMetricsSource(PurifierParser.getMetricsSource(),
                PurifierPropertyFilter.getMetricsSource(),
                BeanInfoIntrospector.getMetricsSource());
    }

    private PurifierMetrics() {
    }

    /**
     * Gets the metrics as a map whose keys are the metric name and whose values are the metric values.
     *
     * @return map
     */
    public static SortedMap<String, Object> asMap() {
        SortedMap<String, Object> metrics = Maps.newTreeMap();
        METRICS_SOURCE.applyMetrics(metrics);
        return metrics;
    }

}
