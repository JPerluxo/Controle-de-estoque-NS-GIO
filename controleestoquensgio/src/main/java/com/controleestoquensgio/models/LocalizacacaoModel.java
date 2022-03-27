package com.controleestoquensgio.models;

import javax.persistence.*;

@Entity
@Table (name = "LOCALIZACOES")
public class LocalizacacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="loc_id")
    private int id;

    @Column(name="loc_predio", nullable = false, length = 200)
    private String predio;
    
    @Column(name="loc_andar", nullable = false, length = 200)
    private String andar;
    
    @Column(name="loc_lado", nullable = false, length = 200)
    private String lado;
    
    @Column(name="loc_referencia", nullable = true, length = 200)
    private String referencia;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPredio() {
        return predio;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getLado() {
        return lado;
    }
    public void setLado(String lado) {
        this.lado = lado;
    }
    public String getAndar() {
        return andar;
    }
    public void setAndar(String andar) {
        this.andar = andar;
    }
    public void setPredio(String predio) {
        this.predio = predio;
    }      
}
