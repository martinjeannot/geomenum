/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config.rootcontext;

import com.geomenum.core.security.components.UserDetailsCoreService;
import com.geomenum.core.security.crypto.CustomBCryptPasswordEncoder;
import com.geomenum.core.security.crypto.CustomPasswordEncoder;
import com.geomenum.rest.RestApiUri;
import com.geomenum.web.WebURLPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * Spring Security Configuration.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCoreService userDetailsCoreService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomPasswordEncoder passwordEncoder() {
        return new CustomBCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsCoreService)
                .passwordEncoder(passwordEncoder());
    }

    // doesn't necessarily belong here but it's a neat way to inject
    // the localeResolver into our custom AuthenticationSuccessHandler
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("locale");
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/favicon.ico",
                        "/robots.txt",
                        "/static/**", // used only for local environments
                        WebURLPath.PUBLIC_HOME,
                        WebURLPath.ERROR,
                        WebURLPath.LOGIN,
                        WebURLPath.SIGN_UP,
                        WebURLPath.TOS,
                        WebURLPath.PRIVACY,
                        RestApiUri.API_ROOT_V1 + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(WebURLPath.LOGIN)
                .successHandler(new CustomAuthenticationSuccessHandler(WebURLPath.PRIVATE_HOME, localeResolver()));
    }
}
