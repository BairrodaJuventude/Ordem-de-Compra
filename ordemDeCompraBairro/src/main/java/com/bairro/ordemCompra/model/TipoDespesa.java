package com.bairro.ordemCompra.model;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.validation.constraints.Max;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class TipoDespesa extends EntityId {

    @NotNull
    @Column(name = "tipo_despesa", nullable = false)
    private int tipoDespesa;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "tp_tipos_despesas", nullable = false, length = 50)
    private TiposDespesas tiposDespesas;

    public TiposDespesas getTiposDespesas() {
        return tiposDespesas;
    }

    public void setTiposDespesas(TiposDespesas tiposDespesas) {
        this.tiposDespesas = tiposDespesas;
    }

    public int getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(int tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    //endregion
}