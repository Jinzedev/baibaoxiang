package com.baibaoxiang.controller;

import com.baibaoxiang.entity.RestBean;
import com.baibaoxiang.entity.vo.request.ConfirmResetVO;
import com.baibaoxiang.entity.vo.request.EmailRegisterVO;
import com.baibaoxiang.entity.vo.request.EmailResetVO;
import com.baibaoxiang.service.AccountService;
import com.baibaoxiang.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于验证相关Controller包含用户的注册、重置密码等操作
 */
@Validated
@RestController
@RequestMapping("/api/auth")
@Tag(name = "登录校验相关", description = "包括用户登录、注册、验证码请求等操作。")
public class AuthorizeController {

    @Resource
    AccountService accountService;

    @Resource
    ControllerUtils utils;


    /**
     * 进行用户注册操作，需要先请求邮件验证码
     *
     * @param vo 注册信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册操作")
    public RestBean<Void> register(@RequestBody @Valid EmailRegisterVO vo) {
        return utils.messageHandle(() ->
                accountService.registerEmailAccount(vo));
    }

    /**
     * 执行密码重置确认，检查验证码是否正确
     *
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-confirm")
    @Operation(summary = "密码重置确认")
    public RestBean<Void> resetConfirm(@RequestBody @Valid ConfirmResetVO vo) {
        return utils.messageHandle(() -> accountService.resetConfirm(vo));
    }

    /**
     * 执行密码重置操作
     *
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-password")
    @Operation(summary = "密码重置操作")
    public RestBean<Void> resetPassword(@RequestBody @Valid EmailResetVO vo) {
        return utils.messageHandle(() ->
                accountService.resetEmailAccountPassword(vo));
    }
}
