
package com.FarmaPet.FarmaPet.model;

import java.util.Date;

import com.FarmaPet.FarmaPet.Enums.MedicamentoAtivo;
import com.FarmaPet.FarmaPet.Enums.TipoUso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "medicamento")
public class ModelMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "principio_ativo", nullable = false)
    private String principioAtivo;

    @Column(nullable = false)
    private String dosagem;

    @Column(name = "especie_indicada", nullable = false)
    private String especieIndicada;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_validade", nullable = false)
    private Date dataValidade;

    @Column(name = "receita_obrigatoria", nullable = false)
    private boolean receitaObrigatoria;

    @Column(name = "peso_indicado")
    private float pesoIndicado;

    @Column(name = "idade_indicada")
    private int idadeIndicada;

    @Enumerated(EnumType.STRING)
    @Column(name = "Medicamento_Ativo", nullable = false)
    private MedicamentoAtivo medicamentoativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_uso", nullable = false)
    private TipoUso tipoUso;

    @Column(length = 255) // Armazena o caminho ou URL da foto
    private String foto;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    public ModelMedicamento(Date dataValidade, String dosagem, String especieIndicada, String foto, int id, int idadeIndicada, MedicamentoAtivo medicamentoativo, String nome, float pesoIndicado, String principioAtivo, int quantidadeEstoque, boolean receitaObrigatoria, TipoUso tipoUso) {
        this.dataValidade = dataValidade;
        this.dosagem = dosagem;
        this.especieIndicada = especieIndicada;
        this.foto = foto;
        this.id = id;
        this.idadeIndicada = idadeIndicada;
        this.medicamentoativo = medicamentoativo;
        this.nome = nome;
        this.pesoIndicado = pesoIndicado;
        this.principioAtivo = principioAtivo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.receitaObrigatoria = receitaObrigatoria;
        this.tipoUso = tipoUso;
    }

    public ModelMedicamento() {
    }



    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public MedicamentoAtivo getMedicamentoativo() {
        return medicamentoativo;
    }

    public void setMedicamentoativo(MedicamentoAtivo medicamentoativo) {
        this.medicamentoativo = medicamentoativo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getEspecieIndicada() {
        return especieIndicada;
    }

    public void setEspecieIndicada(String especieIndicada) {
        this.especieIndicada = especieIndicada;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isReceitaObrigatoria() {
        return receitaObrigatoria;
    }

    public void setReceitaObrigatoria(boolean receitaObrigatoria) {
        this.receitaObrigatoria = receitaObrigatoria;
    }

    public float getPesoIndicado() {
        return pesoIndicado;
    }

    public void setPesoIndicado(float pesoIndicado) {
        this.pesoIndicado = pesoIndicado;
    }

    public int getIdadeIndicada() {
        return idadeIndicada;
    }

    public void setIdadeIndicada(int idadeIndicada) {
        this.idadeIndicada = idadeIndicada;
    }

    public TipoUso getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(TipoUso tipoUso) {
        this.tipoUso = tipoUso;
    }

}
