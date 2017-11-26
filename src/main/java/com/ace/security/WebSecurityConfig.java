package com.ace.security;

import com.ace.service.impl.CustomAuthenticationProvider;
import com.ace.security.filter.JWTLoginFilter;
import com.ace.security.filter.JWTAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 *
 * @author zhaoxinguo on 2017/9/13.
 */
//@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UrlUserService urlUserService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UrlUserService urlUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.urlUserService = urlUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.cors().and().csrf().disable().authorizeRequests()
        //    .antMatchers(HttpMethod.POST, "/oauth/signup").permitAll() // 所有 /users/signup 的POST请求 都放行
        //    .antMatchers("/health").permitAll() // 所有 /users/signup 的POST请求 都放行
        //    .antMatchers("/swagger-ui.html/**").permitAll() // 所有 /users/signup 的POST请求 都放行
        //    .antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**",
        //        "/swagger-ui.html", "/webjars/**", "/images/**").permitAll()
        //    .anyRequest().authenticated()  // 所有请求需要身份认证
        //    .and()
        //    .addFilter(new JWTLoginFilter(authenticationManager()))
        //    .addFilter(new JWTAuthenticationFilter(authenticationManager()));

        http.authorizeRequests().anyRequest().anonymous();

        /**
         * fixed h2-console Refresh to display '' in a frame because it set 'X-Frame-Options' to 'deny'.
         * https://stackoverflow.com/questions/26220083/h2-database-console-spring-boot-load-denied-by-x-frame-options
         * and disable page caching
         */

        http.headers().frameOptions().sameOrigin().cacheControl();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(urlUserService, bCryptPasswordEncoder));
    }

}
