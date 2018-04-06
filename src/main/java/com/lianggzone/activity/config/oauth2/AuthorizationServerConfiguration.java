package com.lianggzone.activity.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.lianggzone.activity.service.SysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CusWebSecurityConfigurationAdapter cusWebSecurityConfigurationAdapter;

    @Autowired
    private SysUserService sysUserService;

    @Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients
			//.jdbc(dataSource) 
			.inMemory()
			.withClient("client")
			.secret("{noop}secret")
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("read", "write")
			.accessTokenValiditySeconds(86400)  // 60 * 60 * 24
			.refreshTokenValiditySeconds(86400) // 60 * 60 * 24
			;
	}
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore)
            .authenticationManager(cusWebSecurityConfigurationAdapter.authenticationManagerBean())
            .userDetailsService(sysUserService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
        //return new JdbcTokenStore(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 支持刷新 token
        tokenServices.setSupportRefreshToken(true); 
        // 设置Token存储方式
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }
}
