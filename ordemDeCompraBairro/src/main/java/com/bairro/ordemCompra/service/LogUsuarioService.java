package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.LogUsuario;
import com.bairro.ordemCompra.repository.LogUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogUsuarioService {

    private ModelMapper modelMapper;

    @Autowired
    private LogUsuarioRepository repository;

    public LogUsuario salvar(LogUsuario entity) {
        return repository.save(entity);
    }

    public List<LogUsuario> buscaTodos(String filter) {
        return repository.findAll(filter, LogUsuario.class);
    }

    public Page<LogUsuario> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, LogUsuario.class, pageable);
    }

    public LogUsuario buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LogUsuario alterar(Long id, LogUsuario entity) {
        Optional<LogUsuario> existingLogUsuarioOptional = repository.findById(id);
        if(existingLogUsuarioOptional.isEmpty()) {
            throw new NotFoundException("Log user não encontrado");
        }
        LogUsuario existingLogUsuario = existingLogUsuarioOptional.get();
        modelMapper.map(entity, existingLogUsuario);
        return repository.save(existingLogUsuario);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}