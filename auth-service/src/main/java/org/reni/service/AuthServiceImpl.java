package org.reni.service;

import lombok.RequiredArgsConstructor;
import org.reni.dtos.LoginDto;
import org.reni.entity.User;
import org.reni.repository.UserRepository;
import org.reni.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

        private final AuthenticationManager authenticationManager;
        private final JwtTokenProvider jwtTokenProvider;
        private final UserRepository userRepository;

        @Override
        public String login(LoginDto loginDto) {
                System.out.println(loginDto + "====================");

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                loginDto.usernameOrEmail(), loginDto.password());
                Authentication authentication = authenticationManager.authenticate(authenticationToken);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return jwtTokenProvider.generateToken(authentication);
        }

        @Override
        public List<String> getUserRoles(String username) {
                User user = userRepository.findByUsernameOrEmail(username, username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                return user.getRoles().stream()
                                .map(role -> role.getName())
                                .collect(Collectors.toList());
        }

        @Override
        public String validateTokenAndGetUsername(String token) {
                if (!jwtTokenProvider.validateToken(token)) {
                        throw new RuntimeException("Invalid token");
                }
                return jwtTokenProvider.getUsernameFromToken(token);
        }

        @Override
        public List<String> getRolesFromToken(String token) {
                if (!jwtTokenProvider.validateToken(token)) {
                        throw new RuntimeException("Invalid token");
                }
                return jwtTokenProvider.getRolesFromToken(token);
        }
}
