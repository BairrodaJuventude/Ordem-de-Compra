package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdemDeCompraRepository extends JpaRepository<OrdemDeCompra, Long>, CustomQuerydslPredicateExecutor<OrdemDeCompra> {
    Optional<OrdemDeCompra> findByNumeroAndUsuarioId(String numero, Long usuarioId);
    Optional<Usuario> findByUsuario(String nome);
}