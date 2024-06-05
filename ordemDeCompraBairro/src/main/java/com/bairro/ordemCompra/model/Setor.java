package com.bairro.ordemCompra.model;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.validation.constraints.Max;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Setor extends EntityId {

    @NotNull
    @Column(name = "cat_setor", columnDefinition = "BIT", nullable = false)
    private Boolean setor;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "tp_Setores", nullable = false, length = 50)
    private Setores setores;

    public Setores getSetores() {
        return setores;
    }

    public void setSetores(Setores setores) {
        this.setores = setores;
    }

    public Boolean getSetor() {
        return setor;
    }

    public void setSetor(Boolean setor) {
        this.setor = setor;
    }

    //endregion
}