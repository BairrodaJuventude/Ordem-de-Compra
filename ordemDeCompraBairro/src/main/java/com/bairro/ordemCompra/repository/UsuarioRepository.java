package com.bairro.ordemCompra.repository;

import com.bairro.ordemCompra.enterprise.CustomQuerydslPredicateExecutor;
import com.bairro.ordemCompra.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, CustomQuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findByToken(String token);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsuario(String username);

    boolean existsById(Long usuarioId);
}