/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "LICENCAS")
public class LicencaModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lic_id;

    @Column(nullable = false, length = 50)
    private String  descricao;

    
    public UUID getLic_id() {
        return lic_id;
    }

    public void setLic_id(UUID lic_id) {
        this.lic_id = lic_id;
    }
    
    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao (){
        return this.descricao;
    }
}