package org.reni.controller;

import lombok.RequiredArgsConstructor;
import org.reni.dtos.AuthResponse;
import org.reni.dtos.LoginDto;
import org.reni.dtos.TokenValidationResponse;
import org.reni.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        var token = authService.login(loginDto);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(token);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/user/{username}/roles")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable String username) {
        List<String> roles = authService.getUserRoles(username);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody String token) {
        try {
            String username = authService.validateTokenAndGetUsername(token);
            List<String> roles = authService.getRolesFromToken(token);
            return ResponseEntity.ok(new TokenValidationResponse(true, username, roles, null));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new TokenValidationResponse(false, null, null, e.getMessage()));
        }
    }
}
