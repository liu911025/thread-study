package com.example.demotest.webservice.service;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

@WebService
public class WeatherServiceImpl implements IWeatherService{
    @Override
    public String query(String city, String weather) {
        System.out.println("查询天气:" + city + ":" + weather);
        return city + ":" + weather;
    }
}
