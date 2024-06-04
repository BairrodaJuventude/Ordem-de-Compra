package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Usuario;
import com.bairro.ordemCompra.service.OrdemDeCompraService;
import com.bairro.ordemCompra.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
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

    @GetMapping("{token}")
    public ResponseEntity<?> findAllByToken(@PathVariable("token") String token) {
        try {
            Usuario usuario = usuarioService.buscaPorToken(token);
            List<OrdemDeCompraDTO> ordensDeCompraDTO = com.bairro.ordemCompra.service.OrdemDeCompraService.buscaTodosPorToken(token).stream()
                    .map(ordemDeCompra -> new OrdemDeCompraDTO(ordemDeCompra.getId(), ordemDeCompra.getNumero(), ordemDeCompra.getData()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ordensDeCompraDTO);
        } catch (HttpClientErrorException.NotFound nfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar os bancos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar os bancos");
        }
    }

    @PostMapping
    public ResponseEntity<OrdemDeCompra> create(@RequestBody @Valid OrdemDeCompra entity) {
        OrdemDeCompra save = com.bairro.ordemCompra.service.OrdemDeCompraService.salvar(entity);
        return ResponseEntity.created(URI.create("/api/banco/" + entity.getId())).body(save);
    }

    @PatchMapping
    public ResponseEntity<?> updateBanco(@RequestBody OrdemDeCompraPatchRequest ordemDeCompraPatchRequest) {
        try {
            // Validar se o ID do usuário está presente
            if (ordemDeCompraPatchRequest.getUsuario() == null || ordemDeCompraPatchRequest.getUsuario().getId() == null) {
                return ResponseEntity.badRequest().body("ID do usuário é obrigatório.");
            }

            // Validar se a ordem de compra antiga esta presente
            if (ordemDeCompraPatchRequest.getAntigoId() == null || ordemDeCompraPatchRequest.getAntigoId().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome do banco antigo é obrigatório.");
            }

            // Validar se a nova ordem de compra esta presente
            if (ordemDeCompraPatchRequest.getNovoId() == null || ordemDeCompraPatchRequest.getNovoId().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome do banco novo é obrigatório.");
            }

            // Usar o serviço para realizar as validações e fazer o patch
            OrdemDeCompra updatedBanco = com.bairro.ordemCompra.service.OrdemDeCompraService.patchOrdemDeCompra(ordemDeCompraPatchRequest);

            // Retornar o banco atualizado
            return ResponseEntity.ok(OrdemDeCompraDTO.fromEntity(updatedBanco));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o banco.");
        }
    }
}