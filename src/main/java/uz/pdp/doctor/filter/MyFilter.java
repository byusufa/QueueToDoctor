package uz.pdp.doctor.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.doctor.entity.User;
import uz.pdp.doctor.jwtservice.JwtService;
import uz.pdp.doctor.repo.UserRepository;

import java.io.IOException;

@Component
public class MyFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public MyFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtService.validate(token)) {
                String username = jwtService.getUserName(token);
                User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
                var auth = new UsernamePasswordAuthenticationToken(
                        user, //user.getUsername
                        null,
                        user.getRoles()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
