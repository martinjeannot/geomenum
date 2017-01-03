/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config;

import com.geomenum.config.rootcontext.WebSecurityConfiguration;
import com.geomenum.core.domainmodel.system.CoreUserFixtures;
import com.geomenum.core.security.components.InMemoryCoreUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * {@link com.geomenum.config.rootcontext.WebSecurityConfiguration} for testing.
 */
@Configuration
public class WebSecurityTestConfiguration extends WebSecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new InMemoryCoreUserDetailsManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsManager userDetailsManager = userDetailsManager();
        auth.userDetailsService(userDetailsManager);

        userDetailsManager.createUser(CoreUserFixtures.standardUser());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}
