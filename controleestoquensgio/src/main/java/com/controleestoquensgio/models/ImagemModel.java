/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "IMAGENS")
public class ImagemModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="img_id")
    private int id;

    @Column(name="img_descricao", nullable = false, length = 50)
    private String  descricao;

    @OneToMany
    private List<ProgramaModel> programas;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public List<ProgramaModel> getProgramas() {
        return programas;
    }
    public void setProgramas(List<ProgramaModel> programas) {
        this.programas = programas;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
}
