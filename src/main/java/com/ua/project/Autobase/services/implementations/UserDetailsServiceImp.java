package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.User;
import com.ua.project.Autobase.services.UserRoleService;
import com.ua.project.Autobase.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userService.findUserByLogin(username).orElseThrow(RuntimeException::new);

        if (currentUser == null) {
            log.error("User \"" + username + "\" not found!");

            throw new RuntimeException("There is not such user with login \"" + username + "\"!");
        }

        List<String> roles = userRoleService.getUserRolesByUserId(currentUser.getId()).orElseThrow(RuntimeException::new);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if (roles != null) {
            grantedAuthorityList = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }

        return new org.springframework.security.core.userdetails.User(
                currentUser.getLogin(),
                currentUser.getPassword(),
                grantedAuthorityList);
    }
}
