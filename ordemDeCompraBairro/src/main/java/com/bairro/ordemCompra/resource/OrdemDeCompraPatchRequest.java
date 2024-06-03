package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.Usuario;

public class OrdemDeCompraPatchRequest {
    private Usuario usuario;
    private String antigoId;
    private String novoId;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAntigoId() {
        return antigoId;
    }

    public void setAntigoId(String antigoId) {
        this.antigoId = antigoId;
    }

    public String getNovoId() {
        return novoId;
    }

    public void setNovoId(String novoId) {
            this.novoId = novoId;
    }
}
