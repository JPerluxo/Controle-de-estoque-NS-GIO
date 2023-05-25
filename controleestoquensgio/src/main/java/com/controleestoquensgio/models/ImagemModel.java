
package com.controleestoquensgio.models;

import java.util.Set;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @Column(name="img_ativo", nullable = false, length = 4)
    private String ativo;

    public ImagemModel () {
        this.ativo = SimOuNao.SIM.name();
    }

    public void addPrograma (ProgramaModel programa) {
        this.programas.add(programa);
    }
    public void removePrograma (ProgramaModel programa) {
        this.programas.remove(programa);
    }
}
