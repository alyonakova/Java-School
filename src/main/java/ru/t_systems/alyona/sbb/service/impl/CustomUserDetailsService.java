package ru.t_systems.alyona.sbb.service.impl;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Data
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService  {

    private UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByLogin(login);

        if (user==null) {
            throw new UsernameNotFoundException("Login not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), "123", //TODO password
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getIsPassenger() ? "CUSTOMER" : "EMPLOYEE";
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
