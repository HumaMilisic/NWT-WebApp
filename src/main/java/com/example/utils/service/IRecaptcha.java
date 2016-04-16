package com.example.utils.service;

/**
 * Created by WorkIt on 16/04/2016.
 */
public interface IRecaptcha {
    boolean isResponseValid(String remoteIp,String response);
}
