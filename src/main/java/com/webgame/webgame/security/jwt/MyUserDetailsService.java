package com.webgame.webgame.security.jwt;


import com.webgame.webgame.domain.Player;
import com.webgame.webgame.domain.Role;
import com.webgame.webgame.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Player player = playerRepository.findByName(email);
        if (player == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Role.ROLE_PLAYER));
        }
        return new CustomUserPrincipal(player.getId(),
                player.getName(), player.getPassword(), true, true, true,
                true, getAuthorities(player.getRole()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    public class CustomUserPrincipal extends org.springframework.security.core.userdetails.User {
        private long id;
        CustomUserPrincipal(long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
