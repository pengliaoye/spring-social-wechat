package org.springframework.social.wechat.connect;

import java.util.Map;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.wechat.api.WeChatAccessGrant;
import org.springframework.util.MultiValueMap;

public class WeChatOAuth2Template extends OAuth2Template {

	private String appid;
	private String appsecret;
	
	public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		this.appid = clientId;
		this.appsecret=clientSecret;
	}
	
	protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
		String openid = (String) response.get("openid");
		return new WeChatAccessGrant(accessToken, scope, refreshToken, expiresIn, openid);
	}

	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		parameters.add("appid", appid);
		parameters.add("secret", appsecret );
		return super.postForAccessGrant(accessTokenUrl, parameters);
	}

}
