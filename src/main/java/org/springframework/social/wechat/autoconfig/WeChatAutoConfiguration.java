package org.springframework.social.wechat.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.wechat.connect.WeChatConnectionFactory;

@Configuration
@ConditionalOnClass(value = { SocialConfigurerAdapter.class, WeChatConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.wechat", value = "app-id")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WeChatAutoConfiguration {

	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(WeChatProperties.class)
	@ConditionalOnWebApplication
	protected static class WeChatConfigurerAdapter extends SocialAutoConfigurerAdapter {

		private final WeChatProperties properties;

		protected WeChatConfigurerAdapter(WeChatProperties properties) {
			this.properties = properties;
		}

		@Override
		protected ConnectionFactory<?> createConnectionFactory() {
			return new WeChatConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
		}

	}

}
