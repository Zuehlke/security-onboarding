package com.zuehlke.zrs.security.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UserSignedInViewModel {
    private final Set<String> roles;

    public UserSignedInViewModel(Collection<? extends GrantedAuthority> authorities) {
        roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toSet());
    }

    public Collection<String> getRoles() {
        return roles;
    }

    @Override
    public int hashCode() {
        return roles != null ? roles.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSignedInViewModel that = (UserSignedInViewModel) o;

        return roles != null ? roles.equals(that.roles) : that.roles == null;

    }

    @Override
    public String toString() {
        return "UserSignedInViewModel{" +
                "roles=" + roles +
                '}';
    }
}
