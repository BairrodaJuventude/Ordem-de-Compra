package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.LogUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogUsuarioRepository extends JpaRepository<LogUsuario, Long>, CustomQuerydslPredicateExecutor<LogUsuario> {

}