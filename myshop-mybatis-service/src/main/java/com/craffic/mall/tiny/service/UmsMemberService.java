package com.craffic.mall.tiny.service;

import com.craffic.mall.tiny.common.api.CommonResult;

/**
 * 会员管理Service
 */
public interface UmsMemberService {

    /**
     * 会员管理Service
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码是否正确
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
