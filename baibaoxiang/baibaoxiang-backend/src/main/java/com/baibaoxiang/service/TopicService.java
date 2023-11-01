package com.baibaoxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baibaoxiang.entity.dto.Interact;
import com.baibaoxiang.entity.dto.Topic;
import com.baibaoxiang.entity.dto.TopicType;
import com.baibaoxiang.entity.vo.request.TopicCreateVO;
import com.baibaoxiang.entity.vo.response.TopicDetailVO;
import com.baibaoxiang.entity.vo.response.TopicPreviewVO;
import com.baibaoxiang.entity.vo.response.TopicTopVO;

import java.util.List;

public interface TopicService extends IService<Topic> {
    List<TopicType> listTypes();
    String createTopic(int uid, TopicCreateVO vo);
    List<TopicPreviewVO> listTopicByPage(int page, int type);
    List<TopicTopVO> listTopTopics();
    TopicDetailVO getTopic(int tid);
    void interact(Interact interact, boolean state);
    List<TopicPreviewVO> listTopicCollects(int uid);
}
