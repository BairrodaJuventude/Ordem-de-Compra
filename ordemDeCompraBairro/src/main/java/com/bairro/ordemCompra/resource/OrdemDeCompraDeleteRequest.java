package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.OrdemDeCompra;

public class OrdemDeCompraDeleteRequest {
    private String Id;
    private UsuarioDTO usuario;
    private OrdemDeCompra ordemDeCompra;

    public String getId() {
        return Id;
    }

    public void setOrdemDeCompra(String banco) {
        this.Id = Id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
