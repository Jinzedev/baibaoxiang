package com.baibaoxiang.controller;

import com.baibaoxiang.entity.RestBean;
import com.baibaoxiang.entity.dto.Account;
import com.baibaoxiang.entity.dto.AccountDetails;
import com.baibaoxiang.entity.vo.request.ChangePasswordVO;
import com.baibaoxiang.entity.vo.request.DetailsSaveVO;
import com.baibaoxiang.entity.vo.request.ModifyEmailVO;
import com.baibaoxiang.entity.vo.request.PrivacySaveVO;
import com.baibaoxiang.entity.vo.response.AccountDetailsVO;
import com.baibaoxiang.entity.vo.response.AccountPrivacyVO;
import com.baibaoxiang.entity.vo.response.AccountVO;
import com.baibaoxiang.service.AccountDetailsService;
import com.baibaoxiang.service.AccountPrivacyService;
import com.baibaoxiang.service.AccountService;
import com.baibaoxiang.utils.Const;
import com.baibaoxiang.utils.ControllerUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    @Resource
    AccountService service;

    @Resource
    AccountDetailsService detailsService;

    @Resource
    AccountPrivacyService privacyService;

    @Resource
    ControllerUtils utils;

    @GetMapping("/info")
    public RestBean<AccountVO> info(@RequestAttribute(Const.ATTR_USER_ID) int id){
        Account account = service.findAccountById(id);
        return RestBean.success(account.asViewObject(AccountVO.class));
    }

    @GetMapping("/details")
    public RestBean<AccountDetailsVO> details(@RequestAttribute(Const.ATTR_USER_ID) int id){
        AccountDetails details = Optional
                .ofNullable(detailsService.findAccountDetailsById(id))
                .orElseGet(AccountDetails::new);
        return RestBean.success(details.asViewObject(AccountDetailsVO.class));
    }

    @PostMapping("/save-details")
    public RestBean<Void> saveDetails(@RequestAttribute(Const.ATTR_USER_ID) int id,
                                      @RequestBody @Valid DetailsSaveVO vo){
        boolean success = detailsService.saveAccountDetails(id, vo);
        return success ? RestBean.success() : RestBean.failure(400, "此用户名已被其他用户使用，请重新更换！");
    }

    @PostMapping("/modify-email")
    public RestBean<Void> modifyEmail(@RequestAttribute(Const.ATTR_USER_ID) int id,
                                      @RequestBody @Valid ModifyEmailVO vo){
        return utils.messageHandle(() -> service.modifyEmail(id, vo));
    }

    @PostMapping("/change-password")
    public RestBean<Void> changePassword(@RequestAttribute(Const.ATTR_USER_ID) int id,
                                         @RequestBody @Valid ChangePasswordVO vo){
        return utils.messageHandle(() -> service.changePassword(id, vo));
    }

    @PostMapping("/save-privacy")
    public RestBean<Void> savePrivacy(@RequestAttribute(Const.ATTR_USER_ID) int id,
                                      @RequestBody @Valid PrivacySaveVO vo){
        privacyService.savePrivacy(id, vo);
        return RestBean.success();
    }

    @GetMapping("/privacy")
    public RestBean<AccountPrivacyVO> privacy(@RequestAttribute(Const.ATTR_USER_ID) int id){
        return RestBean.success(privacyService.accountPrivacy(id).asViewObject(AccountPrivacyVO.class));
    }


}
