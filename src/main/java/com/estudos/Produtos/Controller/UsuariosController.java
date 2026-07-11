package com.estudos.Produtos.Controller;


import com.estudos.Produtos.Dto.UsuariosDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuariosController {

    private final AuthenticationManager manager;

    @PostMapping("/login")
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid UsuariosDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
