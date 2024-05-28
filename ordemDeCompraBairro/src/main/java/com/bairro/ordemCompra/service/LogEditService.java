package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.LogEdit;
import com.bairro.ordemCompra.repository.LogEditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogEditService {

    private ModelMapper modelMapper;

    @Autowired
    private LogEditRepository repository;

    public LogEdit salvar(LogEdit entity) {
        return repository.save(entity);
    }

    public List<LogEdit> buscaTodos(String filter) {
        return repository.findAll(filter, LogEdit.class);
    }

    public Page<LogEdit> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, LogEdit.class, pageable);
    }

    public LogEdit buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LogEdit alterar(Long id, LogEdit entity) {
        Optional<LogEdit> existingLogEditOptional = repository.findById(id);
        if(existingLogEditOptional.isEmpty()) {
            throw new NotFoundException("Log edit não encontrado");
        }
        LogEdit existingLogEdit = existingLogEditOptional.get();
        modelMapper.map(entity, existingLogEdit);
        return repository.save(existingLogEdit);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}