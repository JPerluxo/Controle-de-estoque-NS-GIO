
/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table (name = "PROGRAMAS")
public class ProgramaModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID prg_id;

    @Column(nullable = false, length = 50)
    private String  descricao;

    @Column(nullable = false, length = 200)
    private String observacao;
    
    @ManyToOne
    private LicencaModel licenca;

    public UUID getPrg_id() {
        return prg_id;
    }

    public void setPrg_id(UUID prg_id) {
        this.prg_id = prg_id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LicencaModel getLicenca() {
        return licenca;
    }
    public void setLicenca(LicencaModel licenca) {
        this.licenca = licenca;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
