package org.reni.service;

import org.reni.dtos.LoginDto;

import java.util.List;

public interface AuthService {

    String login(LoginDto loginDto);

    List<String> getUserRoles(String username);

    String validateTokenAndGetUsername(String token);

    List<String> getRolesFromToken(String token);
}
