package com.lianggzone.activity.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import com.lianggzone.activity.service.SysUserService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfiguration
        extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
    private SysUserService sysUserService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService)
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
