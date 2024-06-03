package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Permissao;
import com.bairro.ordemCompra.repository.OrdemDeCompraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class OrdemDeCompraService {

    private ModelMapper modelMapper;

    @Autowired
    private OrdemDeCompraRepository repository;

    public OrdemDeCompra salvar(OrdemDeCompra entity) {
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
        if(existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        modelMapper.map(entity, existingOrdemDeCompra);
        return repository.save(existingOrdemDeCompra);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public OrdemDeCompra patch(Long id, OrdemDeCompraPatchRequest patchRequest) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if(existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        modelMapper.map(patchRequest, existingOrdemDeCompra);
        return repository.save(existingOrdemDeCompra);
    }

    public OrdemDeCompra adicionarPermissao(Long id, Permissao permissao) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if(existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra não encontrada");
        }
        OrdemDeCompra existingOrdemDeCompra = existingOrdemDeCompraOptional.get();
        existingOrdemDeCompra.getPermissoes().add(permissao);
        return repository.save(existingOrdemDeCompra);
    }

    public OrdemDeCompra removerPermissao(Long id, Permissao permissao) {
        Optional<OrdemDeCompra> existingOrdemDeCompraOptional = repository.findById(id);
        if(existingOrdemDeCompraOptional.isEmpty()) {
            throw new NotFoundException("Ordem de compra
}