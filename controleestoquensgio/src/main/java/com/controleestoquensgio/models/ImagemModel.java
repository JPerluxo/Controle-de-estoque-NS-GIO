/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "IMAGENS")
public class ImagemModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID img_id;

    @Column(nullable = false, length = 50)
    private String  descricao;

    @OneToMany
    private List<ProgramaModel> programas;

    public UUID getImg_id() {
        return img_id;
    }

    public void setImg_id(UUID img_id) {
        this.img_id = img_id;
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
