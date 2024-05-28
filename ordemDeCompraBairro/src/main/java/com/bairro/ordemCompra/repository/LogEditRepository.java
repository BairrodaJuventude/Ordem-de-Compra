package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.LogEdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEditRepository extends JpaRepository<LogEdit, Long>, CustomQuerydslPredicateExecutor<LogEdit> {
}