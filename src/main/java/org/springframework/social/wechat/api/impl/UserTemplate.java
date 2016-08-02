package org.springframework.social.wechat.api.impl;

import org.springframework.social.wechat.api.UserInfo;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate implements UserOperations {

	private final RestTemplate restTemplate;
	private String openid;

	public UserTemplate(RestTemplate restTemplate, String openid) {
		this.restTemplate = restTemplate;
		this.openid = openid;
	}

	public UserInfo getUserInfo() {
		UserInfo userInfo = restTemplate.getForObject(
				"https://api.weixin.qq.com/sns/auth?openid=" + openid, UserInfo.class);
		return userInfo;
	}

}
