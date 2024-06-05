package com.bairro.ordemCompra.model;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.validation.constraints.Max;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class Permissao extends EntityId {

    @NotNull
    @Column(name = "cat_permissao", columnDefinition = "BIT", nullable = false)
    private Boolean permissao;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "tp_permissao", nullable = false, length = 50)
    private Permissoes permissoes;

    public @NotNull Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public Boolean getPermissao() {
        return permissao;
    }

    public void setPermissao(Boolean setor) {
        this.permissao = permissao;
    }

    //endregion
}