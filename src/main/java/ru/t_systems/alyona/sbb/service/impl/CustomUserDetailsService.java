package ru.t_systems.alyona.sbb.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.dto.UserDetailsDTO;
import ru.t_systems.alyona.sbb.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Data
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Login not found");
        }
        return UserDetailsDTO.builder()
                .id(user.getId())
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(getGrantedAuthorities(user))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getIsPassenger() ? "ROLE_CUSTOMER" : "ROLE_EMPLOYEE";
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
