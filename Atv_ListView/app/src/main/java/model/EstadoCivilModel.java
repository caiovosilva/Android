package model;

public class EstadoCivilModel {
    private String codigo;
    private String descricao;

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public EstadoCivilModel(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
