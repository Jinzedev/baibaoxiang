package com.baibaoxiang.controller;

import com.baibaoxiang.entity.RestBean;
import com.baibaoxiang.entity.vo.response.*;
import com.baibaoxiang.service.WeatherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    @Resource
    WeatherService service;



    @GetMapping("/weather")
    public RestBean<WeatherVO> weather(double longitude, double latitude){
        WeatherVO vo = service.fetchWeather(longitude, latitude);
        return vo == null ?
                RestBean.failure(400, "获取地理位置信息与天气失败，请联系管理员！") : RestBean.success(vo);
    }













}
