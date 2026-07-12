package com.estudos.Produtos.Controller;


import com.estudos.Produtos.Dto.TokenJwtDto;
import com.estudos.Produtos.Dto.UsuariosDto;
import com.estudos.Produtos.Service.TokenService;
import com.estudos.Produtos.Users.UsuariosModel;
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

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuariosDto dto) {
        var Authtoken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(Authtoken);

        var JWTtoken = tokenService.gerarToken((UsuariosModel) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(JWTtoken));
    }
}
