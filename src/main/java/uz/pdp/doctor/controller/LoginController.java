package uz.pdp.doctor.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.doctor.dto.LoginDto;
import uz.pdp.doctor.entity.User;
import uz.pdp.doctor.jwtservice.JwtService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/login")
@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("roles", user.getRoles());
        return ResponseEntity.ok(response);

    }
}
