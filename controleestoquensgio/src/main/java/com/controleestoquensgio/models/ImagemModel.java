
package com.controleestoquensgio.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "IMAGENS")
public class ImagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="img_id")
    private int id;

    @Column(name="img_descricao", nullable = false, length = 50)
    private String  descricao;

    @ManyToMany
    private Set<ProgramaModel> programas;

    public int getId() {
        return id;
    }  
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void addPrograma (ProgramaModel programa) {
        this.programas.add(programa);
    }
    public void removePrograma (ProgramaModel programa) {
        this.programas.remove(programa);
    }
    public Set<ProgramaModel> getProgramas() {
        return programas;
    }
    public void setProgramas(Set<ProgramaModel> programas) {
        this.programas = programas;
    }
}
