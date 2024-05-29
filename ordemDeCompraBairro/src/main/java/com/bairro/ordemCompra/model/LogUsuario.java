package com.bairro.ordemCompra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class LogUsuario extends EntityId {
    @NotNull @NotBlank
    @Column(name = "log_data", nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String log_data;

    //region Getters e Setters
    public String getLog_data() {
        return log_data;
    }

    public void setLog_data(String log_data) {
        this.log_data = log_data;
    }
    //endregion
}