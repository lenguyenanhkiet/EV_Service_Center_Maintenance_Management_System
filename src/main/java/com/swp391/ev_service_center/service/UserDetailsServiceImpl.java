package com.swp391.ev_service_center.service;

import com.swp391.ev_service_center.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String loginIdentifier) throws UsernameNotFoundException {
        if (loginIdentifier == null || loginIdentifier.isBlank()) {
            throw new UsernameNotFoundException("Login identifier is empty");
        }
        if(loginIdentifier.contains("@")){
            return userRepository.findByEmail(loginIdentifier)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginIdentifier));
        }else{
            return userRepository.findByUsername(loginIdentifier)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + loginIdentifier));
        }
    }
}
