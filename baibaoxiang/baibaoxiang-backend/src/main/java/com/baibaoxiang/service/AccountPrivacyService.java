package com.baibaoxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baibaoxiang.entity.dto.AccountPrivacy;
import com.baibaoxiang.entity.vo.request.PrivacySaveVO;

public interface AccountPrivacyService extends IService<AccountPrivacy> {
    void savePrivacy(int id, PrivacySaveVO vo);
    AccountPrivacy accountPrivacy(int id);
}
