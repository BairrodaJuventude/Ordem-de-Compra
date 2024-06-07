package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long>, CustomQuerydslPredicateExecutor<TipoDespesa> {
    List<TipoDespesa> findByNomeContaining(String nome);
    boolean existsById(Long tipoDespesaId);
}