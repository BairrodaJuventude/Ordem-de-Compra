package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Usuario;
import com.bairro.ordemCompra.service.NotFoundException;
import com.bairro.ordemCompra.service.OrdemDeCompraService;
import com.bairro.ordemCompra.service.UsuarioService;
import com.bairro.ordemCompra.util.GeraTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/banco")
public class OrdemDeCompraController extends AbstractController {
    @Autowired
    private OrdemDeCompraService OrdemDeCompraService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<OrdemDeCompra> create(@RequestBody @Valid OrdemDeCompra entity) {
        OrdemDeCompra save = OrdemDeCompraService.salvar(entity);
        return ResponseEntity.created(URI.create("/api/banco/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity<Page<OrdemDeCompraDTO>> findAll(@RequestParam(required = false) String filter,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "15") int size) {
        Page<OrdemDeCompra> ordensDeCompra = OrdemDeCompraService.buscaTodos(filter, PageRequest.of(page, size));
        Page<OrdemDeCompraDTO]> ordensDeCompraDTO = OrdemDeCompraDTO.fromEntity((OrdemDeCompra) ordensDeCompra);
        return ResponseEntity.ok(ordensDeCompra);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdemDeCompra> findById(@PathVariable("id") Long id) {
        OrdemDeCompra banco = OrdemDeCompraService.buscaPorId(id);
        return ResponseEntity.ok(banco);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrdemDeCompra> update(@PathVariable("id") Long id, @RequestBody OrdemDeCompra entity) {
        try {
            OrdemDeCompra alterado = OrdemDeCompraService.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        }
        catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean autenticador = usuarioService.autenticar(username, password);
        if (autenticador) {
            // Gera o token JWT
            String token = GeraTokens.gerarToken(username);
            resp.setHeader("Authorization", "Bearer " + token);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}