package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;

@Data
@Builder
public class UserDetailsDTO implements UserDetails {

    BigInteger id;
    Collection<? extends GrantedAuthority> authorities;
    String password;
    String username;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;
}
