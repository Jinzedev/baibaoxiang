package com.baibaoxiang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baibaoxiang.entity.dto.Account;
import com.baibaoxiang.entity.dto.AccountDetails;
import com.baibaoxiang.entity.vo.request.DetailsSaveVO;
import com.baibaoxiang.mapper.AccountDetailsMapper;
import com.baibaoxiang.service.AccountDetailsService;
import com.baibaoxiang.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailsServiceImpl extends ServiceImpl<AccountDetailsMapper, AccountDetails> implements AccountDetailsService {

    @Resource
    AccountService service;

    @Override
    public AccountDetails findAccountDetailsById(int id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public synchronized boolean saveAccountDetails(int id, DetailsSaveVO vo) {
        Account account = service.findAccountByNameOrEmail(vo.getUsername());
        if(account == null || account.getId() == id) {
            service.update()
                    .eq("id", id)
                    .set("username", vo.getUsername())
                    .update();
            this.saveOrUpdate(new AccountDetails(
                    id, vo.getGender(), vo.getPhone(),
                    vo.getQq(), vo.getWx(), vo.getDesc()
            ));
            return true;
        }
        return false;
    }
}
