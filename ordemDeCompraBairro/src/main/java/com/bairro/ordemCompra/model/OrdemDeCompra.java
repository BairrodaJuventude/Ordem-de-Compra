package com.bairro.ordemCompra.model;

import javax.persistence.Column;

public class OrdemDeCompra extends EntityId{

    @Column(name = "numero", nullable = false)
    private Integer unidade;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "tipoDespesa", nullable = false)
    private TipoDespesa tipoDespesa;
    @Column(name = "valorUnitario", nullable = false)
    private Double valorUnitario;
    @Column(name = "valorTotal", nullable = false)
    private Double valorTotal;
    @Column(name = "valorCompra", nullable = false)
    private Double valorCompra;
    @Column(name = "Fornecedor", nullable = false)
    private String Fornecedor;
    @Column(name = "setor", nullable = false)
    private Setor setor;
    @Column(name = "dataCompra", nullable = false)
    private String dataCompra;
    //region Getters e Setters
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
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }
    public void setTipoDespesa(TipoDespesa tipoDespesa) {
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
    public String getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    //endregion
}
