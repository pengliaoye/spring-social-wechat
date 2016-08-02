package org.springframework.social.wechat.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.WeChat;

public class WeChatTemplate extends AbstractOAuth2ApiBinding implements WeChat{
	
	private String openid;
	
    public WeChatTemplate(String accessToken, String openid) {
        super(accessToken);
        this.openid = openid;
    }

	public UserOperations userOperations() {
		return new UserTemplate(getRestTemplate(), openid);
	}

}
