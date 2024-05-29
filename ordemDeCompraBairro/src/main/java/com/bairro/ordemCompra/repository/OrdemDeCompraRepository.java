package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.LogUsuario;
import com.bairro.ordemCompra.model.OrdemDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemDeCompraRepository extends JpaRepository<LogUsuario, Long>, CustomQuerydslPredicateExecutor<OrdemDeCompra> {

}