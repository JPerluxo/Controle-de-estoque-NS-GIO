package com.controleestoquensgio.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "NOTAS_FISCAIS")
public class NotaFiscalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nof_id")
    private int id;

    @Column(name="nof_numero", nullable = false, length = 20)
    private String numero;
    
    @Column(name="nof_dt", nullable = false)
    private Date date;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
