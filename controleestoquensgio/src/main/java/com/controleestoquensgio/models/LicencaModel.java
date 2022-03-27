/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.models;

import javax.persistence.*;

@Entity
@Table(name = "LICENCAS")
public class LicencaModel {
    
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