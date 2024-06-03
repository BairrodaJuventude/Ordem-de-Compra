package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.OrdemDeCompra;
import com.bairro.ordemCompra.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdemDeCompraRepository extends JpaRepository<OrdemDeCompra, Long>, CustomQuerydslPredicateExecutor<OrdemDeCompra> {
    Optional<OrdemDeCompra> findByOrdemDeCompraAndUsuarioId(String banco, Long usuarioId);
    List<OrdemDeCompra> findByUsuario(Usuario usuario);
}