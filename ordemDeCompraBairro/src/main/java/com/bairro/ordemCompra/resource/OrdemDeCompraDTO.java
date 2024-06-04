package com.bairro.ordemCompra.resource;

import com.bairro.ordemCompra.model.OrdemDeCompra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrdemDeCompraDTO {
    private Long id;
    private String ordemDeCompra;

    private LocalDateTime data;

    
    // Construtor vazio
    public OrdemDeCompraDTO() {
    }

    // Construtor com os campos desejados
    public OrdemDeCompraDTO(Long id, String banco, LocalDateTime data) {
        this.id = id;
        this.ordemDeCompra = banco;
        this.data = data;
    }

    //region Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return ordemDeCompra;
    }

    public void setNumero(String ordemDeCompra) {
        this.ordemDeCompra = ordemDeCompra;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    //endregion

    // Método para criar um DTO a partir de uma entidade Banco
    public static OrdemDeCompraDTO fromEntity(OrdemDeCompra originalOrdemDeCompra) {
        return new OrdemDeCompraDTO(originalOrdemDeCompra.getId(), originalOrdemDeCompra.getNumero(), originalOrdemDeCompra.getData());
    }

    // Método para converter uma lista de entidades Banco para uma lista de DTOs
    public static List<OrdemDeCompraDTO> fromEntityList(List<OrdemDeCompra> ordensDeCompra) {
        return ordensDeCompra.stream().map(OrdemDeCompraDTO::fromEntity).collect(Collectors.toList());
    }

}
