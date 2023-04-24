package com.controleestoquensgio.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "CONTRATO_COMODATO")
public class ContratoComodatoModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ccm_id")
    private int id;

    @Column(name="ccm_dtAssinatura", nullable = false, length = 50)
    private Date dataAssinatura; 
    
    @Column(name="ccm_caminhoArquivo", nullable = false, length = 500)
    private String caminhoArquivo;
    
    @Column(name="ccm_status", nullable = false, length = 50)
    private String status;

    @ManyToOne
    private ColaboradorModel colaborador;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
    public Date getDataAssinatura() {
        return dataAssinatura;
    }
    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }
    public ColaboradorModel getColaborador() {
        return colaborador;
    }
    public void setColaborador(ColaboradorModel colaborador) {
        this.colaborador = colaborador;
    }
}