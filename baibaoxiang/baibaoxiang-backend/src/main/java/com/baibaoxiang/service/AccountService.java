package com.baibaoxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baibaoxiang.entity.dto.Account;
import com.baibaoxiang.entity.vo.request.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    Account findAccountById(int id);
    String registerEmailAccount(EmailRegisterVO info);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);
    String modifyEmail(int id, ModifyEmailVO vo);
    String changePassword(int id, ChangePasswordVO vo);
}
