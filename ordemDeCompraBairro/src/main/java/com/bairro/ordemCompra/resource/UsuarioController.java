package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.util.GeraTokens;
import com.bairro.ordemCompra.model.Usuario;
import com.bairro.ordemCompra.service.NotFoundException;
import com.bairro.ordemCompra.service.SenhaInvalidaException;
import com.bairro.ordemCompra.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends AbstractController {
    @Autowired
    private UsuarioService service;
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            boolean autenticador = service.autenticar(username, password);
            if (autenticador) {
                // Gera o token JWT
                String token = GeraTokens.gerarToken(username);
                resp.setHeader("Authorization", "Bearer " + token);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authorizationHeader = httpRequest.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                try {
                    GeraTokens.verificaToken(token);
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    throw new ServletException("Token inválido", e);
                }
            } else {
                throw new ServletException("Faltando ou mal formatado header Authorization");
            }
        }
        public void init(FilterConfig filterConfig) throws ServletException {}
        public void destroy() {}

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Usuario entity, HttpServletResponse response) {
        try {

            Usuario save = service.salvar(entity);

            // Retorna apenas o token em caso de sucesso
            return ResponseEntity.ok(Collections.singletonMap("token", save.getToken()));
        } catch (HttpClientErrorException.BadRequest nfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username ou email já estão sendo utilizados");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Username ou email já estão sendo utilizados");
        }
    }

    @GetMapping("{usuario}")
    public ResponseEntity<?> findByUsuario(@PathVariable("usuario") String usuario) {
        try {
            service.buscaPorUsuario(usuario);
            return ResponseEntity.ok(usuario);
        } catch (HttpClientErrorException.NotFound nfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuário não encontrado");
        }
    }

    @PatchMapping("{token}")
    public ResponseEntity<?> updateSenhaByToken(
            @PathVariable("token") String token,
            @RequestBody Map<String, String> requestBody
    ) {
        try {
            String senhaAtual = requestBody.get("senha_antiga");
            String novaSenha = requestBody.get("password");

            Usuario alterado = service.alterarSenha(token, senhaAtual, novaSenha);

            return ResponseEntity.ok("Senha atualizada com sucesso");
        } catch (SenhaInvalidaException sie) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha atual incorreta");
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a senha");
        }
    }
}