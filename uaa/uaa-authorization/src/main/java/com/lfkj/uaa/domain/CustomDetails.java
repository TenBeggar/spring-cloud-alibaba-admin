package com.lfkj.uaa.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class CustomDetails extends org.springframework.security.core.userdetails.User {

    public CustomDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    public CustomDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
