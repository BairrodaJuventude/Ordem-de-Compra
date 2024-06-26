package com.bairro.ordemCompra.model;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrdemDeCompra extends EntityId{

    @NotNull
    @NotBlank
    @Column(name = "numero", nullable = false)
    private String numero;
    @NotNull @NotBlank
    @Column(name = "unidade", nullable = false)
    private Integer unidade;
    @NotNull @NotBlank
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @NotNull @NotBlank
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @NotNull @NotBlank
    @Column(name = "tipoDespesa", nullable = false)
    private TiposDespesas tipoDespesa;
    @NotNull @NotBlank
    @Column(name = "valorUnitario", nullable = false)
    private Double valorUnitario;
    @NotNull @NotBlank
    @Column(name = "valorTotal", nullable = false)
    private Double valorTotal;
    @NotNull @NotBlank
    @Column(name = "valorCompra", nullable = false)
    private Double valorCompra;
    @NotNull @NotBlank
    @Column(name = "Fornecedor", nullable = false)
    private String Fornecedor;
    @NotNull @NotBlank
    @Column(name = "setor", nullable = false)
    private Setor setor;
    @Column(name = "dataCompra", nullable = false)
    @NotNull @NotBlank
    private LocalDateTime data;
    //region Getters e Setters
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Integer getUnidade() {
        return unidade;
    }
    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public TiposDespesas getTipoDespesa() {
        return tipoDespesa;
    }
    public void setTipoDespesa(TiposDespesas tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Double getValorCompra() {
        return valorCompra;
    }
    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }
    public String getFornecedor() {
        return Fornecedor;
    }
    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }
    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    //endregion
}
