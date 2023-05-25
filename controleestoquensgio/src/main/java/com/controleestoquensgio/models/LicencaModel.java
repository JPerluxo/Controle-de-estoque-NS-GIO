/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "LICENCAS")
public class LicencaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="lic_id")
    private int id;

    @Column(name="lic_descricao", nullable = false, length = 50)
    private String  descricao;

    @Column(name="lic_ativo", nullable = false, length = 4)
    private String ativo;

    public LicencaModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}