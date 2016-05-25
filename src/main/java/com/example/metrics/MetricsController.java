package com.example.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WorkIt on 25/05/2016.
 */
@RestController
public class MetricsController {
    @Autowired
    private IMetricService metricService;
    @Autowired
    private IAuthMetricService authMetricService;


    @RequestMapping(value = "/metric-graph-data/status",method = RequestMethod.GET)
    @ResponseBody
    public Object[][] getMetricData(){
        return metricService.getGraphData();
    }

    @RequestMapping(value = "/metric-graph-data/auth",method = RequestMethod.GET)
    @ResponseBody
    public Object[][] getAuthMetricData(){
        return authMetricService.getGraphData();
    }
}
