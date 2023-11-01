package com.baibaoxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baibaoxiang.entity.dto.AccountDetails;
import com.baibaoxiang.entity.vo.request.DetailsSaveVO;

public interface AccountDetailsService extends IService<AccountDetails> {
    AccountDetails findAccountDetailsById(int id);
    boolean saveAccountDetails(int id, DetailsSaveVO vo);
}
