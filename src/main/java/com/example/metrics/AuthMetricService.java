package com.example.metrics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by WorkIt on 25/05/2016.
 */
public class AuthMetricService implements IMetricService {

    private ConcurrentMap<String, Integer> loginMetric;
    private ConcurrentMap<String,ConcurrentHashMap<String,Integer>> metricMap;
    private ConcurrentMap<String, ConcurrentHashMap<String, Integer>> timeMap;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public AuthMetricService(){
        loginMetric = new ConcurrentHashMap<String, Integer>();
        metricMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>();
        timeMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>();
    }

    @Override
    public void increaseCount(String request, int status) {
        String por;
        if(status==1){
            por = "login_success";
        }else{
            por = "login_error";
        }
        increaseMainMetric(request, por);
        increaseStatusMetric(por);
        updateTimeMap(por);
    }

    @Override
    public Map getFullMetric() {
        return metricMap;
    }

    @Override
    public Map getStatusMetric() {
        return loginMetric;
    }

    @Override
    public Object[][] getGraphData() {
        final int colCount = loginMetric.keySet().size() + 1;
        final Set<String> allStatus = loginMetric.keySet();
        final int rowCount = timeMap.keySet().size() + 1;

        final Object[][] result = new Object[rowCount][colCount];
        result[0][0] = "Time";

        int j = 1;
        for (final String status : allStatus) {
            result[0][j] = status;
            j++;
        }
        int i = 1;
        ConcurrentMap<String, Integer> tempMap;
        for (final Map.Entry<String, ConcurrentHashMap<String, Integer>> entry : timeMap.entrySet()) {
            result[i][0] = entry.getKey();
            tempMap = entry.getValue();
            for (j = 1; j < colCount; j++) {
                result[i][j] = tempMap.get(result[0][j]);
                if (result[i][j] == null) {
                    result[i][j] = 0;
                }
            }
            i++;
        }

        return result;
    }

    private void updateTimeMap(final String status){
        final String time = dateFormat.format(new Date());
        ConcurrentHashMap<String,Integer> statusMap = timeMap.get(time);

        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<String, Integer>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        timeMap.put(time, statusMap);
    }

    private void increaseStatusMetric(final String status){
        final Integer statusCount = loginMetric.get(status);
        if(statusCount == null){
            loginMetric.put(status,1);
        }else{
            loginMetric.put(status,statusCount + 1);
        }
    }

    private void increaseMainMetric(final String request, final String status){
        ConcurrentHashMap<String,Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<String, Integer>();
        }

        Integer count = statusMap.get(status);
        if(count == null){
            count = 1;
        }else {
            count++;
        }

        statusMap.put(status,count);
        metricMap.put(request,statusMap);
    }
}
