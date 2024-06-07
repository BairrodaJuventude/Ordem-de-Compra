package com.bairro.ordemCompra.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

//Optei por separar a classe Usuario em Usuario e UsuarioAdmin para facilitar a implementação das suas funcionalidades
@MappedSuperclass
public class Usuario extends EntityId {
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    private String token;

    //region getters e setters
    public String getUsuario() {
        return usuario;
    }
    public String setUsuario(String usuario) {
        this.usuario = usuario;
        return usuario;
    }
    public String getEmail() {
        return email;
    }
    public String setEmail(String email) {
        this.email = email;
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String setPassword(String password) {
        this.password = password;
        return password;
    }
    public String getToken() {
        return token;
    }
    //endregion
}

