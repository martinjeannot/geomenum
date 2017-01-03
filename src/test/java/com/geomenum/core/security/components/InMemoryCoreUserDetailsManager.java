package com.geomenum.core.security.components;

import com.geomenum.core.domainmodel.system.CoreUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * This class exists because Spring default InMemoryUserDetailsManager doesn't return
 * instances of types it's given. It only returns instances of UserDetails which isn't
 * good if you want to work with your own User class.
 *
 * @see org.springframework.security.provisioning.InMemoryUserDetailsManager
 */
public class InMemoryCoreUserDetailsManager implements UserDetailsManager {

    private final Map<String, CoreUser> users = new HashMap<>();

    @Override
    public void createUser(UserDetails user) {
        Assert.isTrue(!userExists(user.getUsername()));

        users.put(user.getUsername().toLowerCase(), (CoreUser) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        Assert.isTrue(userExists(user.getUsername()));

        users.put(user.getUsername().toLowerCase(), (CoreUser) user);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username.toLowerCase());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CoreUser user = users.get(username.toLowerCase());

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return CoreUser.of(user.toMap());
    }
}
