/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.security.components;

import com.geomenum.core.services.system.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCoreService implements UserDetailsService {

    @Autowired
    private UserCoreService userCoreService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userCoreService.findUserByUsername(username);
        if(userDetails != null) {
            return userDetails;
        } else {
            throw new UsernameNotFoundException("Cannot find user : " + username);
        }
    }
}
