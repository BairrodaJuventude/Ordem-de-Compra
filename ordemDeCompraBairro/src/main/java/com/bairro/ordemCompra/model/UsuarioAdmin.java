package com.bairro.ordemCompra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class UsuarioAdmin extends Usuario{

    private String email;

    private String password;

    // anotacao @Enumerated para dizer que o campo é um enum e o EnumType.STRING para dizer que o valor do enum é uma string
    @Enumerated(EnumType.STRING)
    @Column(name = "permissoes", nullable = false)
    private Permissoes permissoes;
    //region Getters e Setters

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

        //endregion
}

