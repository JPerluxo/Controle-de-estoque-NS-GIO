package com.controleestoquensgio.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "NOTAS_FISCAIS")
public class NotaFiscalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nof_id")
    private int id;

    @Column(name="nof_numero", nullable = false, length = 20)
    private String numero;
    
    @Column(name="nof_dt")
    private Date data;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Date getData() {
        return data;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
