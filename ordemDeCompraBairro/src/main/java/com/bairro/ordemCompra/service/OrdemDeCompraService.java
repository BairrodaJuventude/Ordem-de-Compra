package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Permissoes;
import com.bairro.ordemCompra.model.Usuario;
import com.bairro.ordemCompra.repository.OrdemDeCompraRepository;
import com.bairro.ordemCompra.resource.OrdemDeCompraPatchRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrdemDeCompraService {

    private ModelMapper modelMapper;
    @Autowired
    private static OrdemDeCompraRepository repository;

    public static OrdemDeCompra salvar(OrdemDeCompra entity) {
        return repository.save(entity);
    }

    public List<OrdemDeCompra> buscaTodos(String filter) {
        return repository.findAll(filter, OrdemDeCompra.class);
    }

    public Page<OrdemDeCompra> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, OrdemDeCompra.class, pageable);
    }

    public OrdemDeCompra buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public OrdemDeCompra alterar(Long id, OrdemDeCompra entity) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if (existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        modelMapper.map(entity, existingOrdemDeCompra);
        return repository.save(existingOrdemDeCompra);
    }
    public static Usuario buscaPorUsuario(String usuario) {
        Optional<Usuario> usuarioOptional = repository.findByUsuario(usuario);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return usuarioOptional.get();
    }

    public static OrdemDeCompra patchOrdemDeCompra(OrdemDeCompraPatchRequest ordemDeCompraPatchRequest) {

        OrdemDeCompra ordemDeCompra = repository.findByNumeroAndUsuarioId(ordemDeCompraPatchRequest.getAntigoId(), ordemDeCompraPatchRequest.getUsuario().getId())
                .orElseThrow(() -> new NotFoundException("Ordem de compra não encontrada"));

        Optional<OrdemDeCompra> existingBancoOptional = repository.findByNumeroAndUsuarioId(ordemDeCompraPatchRequest.getNovoId(), ordemDeCompraPatchRequest.getUsuario().getId());
        if (existingBancoOptional.isPresent()) {
            throw new RuntimeException("O novo nome já está cadastrado para este usuário.");
        }

        ordemDeCompra.setNumero(ordemDeCompraPatchRequest.getNovoId());
        return repository.save(ordemDeCompra);
    }


    public void remover(Long id) {
        repository.deleteById(id);
    }

    public OrdemDeCompra patch(Long id, OrdemDeCompraPatchRequest patchRequest) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if (existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        modelMapper.map(patchRequest, existingOrdemDeCompra);
        return repository.save(existingOrdemDeCompra);
    }

    public OrdemDeCompra adicionarPermissao(Long id, Permissoes permissao) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if (existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        return repository.save(existingOrdemDeCompra);
    }

    public OrdemDeCompra removerPermissao(Long id, Permissoes permissao) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if (existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        return null;
    }
}