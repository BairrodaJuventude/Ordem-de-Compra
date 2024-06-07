package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.TipoDespesa;
import com.bairro.ordemCompra.repository.TipoDespesaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDespesaService {

    private ModelMapper modelMapper;

    @Autowired
    private TipoDespesaRepository repository;

    public TipoDespesa salvar(TipoDespesa entity) {
        return repository.save(entity);
    }

    public List<TipoDespesa> buscaTodos(String filter) {
        return repository.findAll(filter, TipoDespesa.class);
    }

    public Page<TipoDespesa> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, TipoDespesa.class, pageable);
    }

    public TipoDespesa buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TipoDespesa alterar(Long id, TipoDespesa entity) {
        Optional<TipoDespesa> existingMetodoPagamentoOptional = repository.findById(id);
        if(existingMetodoPagamentoOptional.isEmpty()) {
            throw new NotFoundException("Método de pagamento não encontrado");
        }
        TipoDespesa existingTipoDespesa = existingMetodoPagamentoOptional.get();
        modelMapper.map(entity, existingTipoDespesa);
        return repository.save(existingTipoDespesa);
    }
}