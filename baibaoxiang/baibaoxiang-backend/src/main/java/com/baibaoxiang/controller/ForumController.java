package com.baibaoxiang.controller;

import com.baibaoxiang.entity.RestBean;
import com.baibaoxiang.entity.dto.Interact;
import com.baibaoxiang.entity.vo.request.TopicCreateVO;
import com.baibaoxiang.entity.vo.response.*;
import com.baibaoxiang.service.TopicService;
import com.baibaoxiang.service.WeatherService;
import com.baibaoxiang.utils.Const;
import com.baibaoxiang.utils.ControllerUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    @Resource
    WeatherService service;

    @Resource
    TopicService topicService;

    @Resource
    ControllerUtils utils;

    @GetMapping("/weather")
    public RestBean<WeatherVO> weather(double longitude, double latitude){
        WeatherVO vo = service.fetchWeather(longitude, latitude);
        return vo == null ?
                RestBean.failure(400, "获取地理位置信息与天气失败，请联系管理员！") : RestBean.success(vo);
    }

    @GetMapping("/types")
    public RestBean<List<TopicTypeVO>> listTypes(){
        return RestBean.success(topicService
                .listTypes()
                .stream()
                .map(type -> type.asViewObject(TopicTypeVO.class))
                .toList());
    }

    @PostMapping("/create-topic")
    public RestBean<Void> createTopic(@Valid @RequestBody TopicCreateVO vo,
                                      @RequestAttribute(Const.ATTR_USER_ID) int id) {
        return utils.messageHandle(() -> topicService.createTopic(id, vo));
    }

    @GetMapping("/list-topic")
    public RestBean<List<TopicPreviewVO>> listTopic(@RequestParam @Min(0) int page,
                                                    @RequestParam @Min(0) int type) {
        return RestBean.success(topicService.listTopicByPage(page + 1, type));
    }

    @GetMapping("/top-topic")
    public RestBean<List<TopicTopVO>> topTopic(){
        return RestBean.success(topicService.listTopTopics());
    }

    @GetMapping("/topic")
    public RestBean<TopicDetailVO> topic(@RequestParam @Min(0) int tid){
        return RestBean.success(topicService.getTopic(tid));
    }

    @GetMapping("/interact")
    public RestBean<Void> interact(@RequestParam @Min(0) int tid,
                                   @RequestParam @Pattern(regexp = "(like|collect)") String type,
                                   @RequestParam boolean state,
                                   @RequestAttribute(Const.ATTR_USER_ID) int id) {
        topicService.interact(new Interact(tid, id, new Date(), type), state);
        return RestBean.success();
    }

    @GetMapping("/collects")
    public RestBean<List<TopicPreviewVO>> collects(@RequestAttribute(Const.ATTR_USER_ID) int id){
        return RestBean.success(topicService.listTopicCollects(id));
    }
}
