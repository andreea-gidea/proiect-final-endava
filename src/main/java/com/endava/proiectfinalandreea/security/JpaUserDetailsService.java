package com.endava.proiectfinalandreea.security;

import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.model.SecurityClient;
import com.endava.proiectfinalandreea.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> dbUser = userRepository.findUserByUserName(username);
        UserEntity user = dbUser.orElseThrow(() -> new UsernameNotFoundException("User was not found in the database"));
        return new SecurityClient(user);
    }
}
