package br.gsalles.runapi.controller;

import br.gsalles.runapi.dto.AuthDTO;
import br.gsalles.runapi.rdto.AuthResponseDTO;
import br.gsalles.runapi.repository.UserRepository;
import br.gsalles.runapi.security.JwtProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getPassword()
        ));
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
