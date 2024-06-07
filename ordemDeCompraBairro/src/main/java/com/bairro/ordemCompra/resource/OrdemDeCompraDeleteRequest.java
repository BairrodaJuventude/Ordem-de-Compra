package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.OrdemDeCompra;

public class OrdemDeCompraDeleteRequest extends OrdemDeCompra {
    private String Id;
    private UsuarioDTO usuario;

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
