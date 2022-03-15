/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "LICENCAS")
public class LicencaModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="lic_id")
    private int id;

    @Column(name="lic_descricao", nullable = false, length = 50)
    private String  descricao;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao (){
        return this.descricao;
    }
}