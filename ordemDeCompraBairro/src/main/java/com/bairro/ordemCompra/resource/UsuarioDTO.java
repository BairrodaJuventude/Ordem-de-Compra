package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
//    private String token;

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion

    //region Constructors
    public static UsuarioDTO fromEntity(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsuario());
        dto.setEmail(usuario.getEmail());
//        dto.setToken(usuario.getToken());
        return dto;
    }

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.getId());
        usuario.setUsuario(this.getUsername());
        usuario.setEmail(this.getEmail());
//        usuario.setToken(this.getToken());
        return usuario;
    }

    public static List<UsuarioDTO> fromEntity(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> fromEntity(usuario)).collect(Collectors.toList());
    }

    public static Page<UsuarioDTO> fromEntity(Page<Usuario> usuarios) {
        List<UsuarioDTO> usuariosFind = usuarios.stream().map(usuario -> fromEntity(usuario)).collect(Collectors.toList());
        Page<UsuarioDTO> usuariosDTO = new PageImpl<>(usuariosFind, usuarios.getPageable(), usuarios.getTotalElements());
        return usuariosDTO;
    }
    //endregion
}