package io.github.walkin.purifier.metric.source;

import com.google.common.collect.ImmutableList;

import java.util.Map;

/**
 * A source that pull metrics from multiple other sources.
 */
public class CompositePurifierMetricsSource implements PurifierMetricsSource {

    private final ImmutableList<PurifierMetricsSource> sources;

    public CompositePurifierMetricsSource(PurifierMetricsSource... sources) {
        this.sources = ImmutableList.copyOf(sources);
    }

    @Override
    public void applyMetrics(Map<String, Object> map) {
        for (PurifierMetricsSource source : sources) {
            source.applyMetrics(map);
        }
    }

}
