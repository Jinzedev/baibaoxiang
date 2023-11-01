package com.baibaoxiang.service;

import com.baibaoxiang.entity.vo.response.WeatherVO;

public interface WeatherService {
    WeatherVO fetchWeather(double longitude, double latitude);
}
