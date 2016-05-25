package com.example.metrics;

import java.util.Map;

/**
 * Created by WorkIt on 25/05/2016.
 */
public interface IMetricService {
    void increaseCount(final String request, final int status);

    Map getFullMetric();

    Map getStatusMetric();

    Object[][] getGraphData();
}
