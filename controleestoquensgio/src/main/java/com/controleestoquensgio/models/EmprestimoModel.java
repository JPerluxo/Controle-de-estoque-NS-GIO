package com.controleestoquensgio.models;

import java.sql.Date;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "EMPRESTIMOS")
public class EmprestimoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="emp_id")
    private int id;

    @Column(name="emp_dtDisponibilizacao", nullable = false)
    private Date dataDisponibilizacao;
    
    @Column(name="emp_dtDevolucao", nullable = false)
    private Date dataDevolucao;

    @ManyToOne
    private ColaboradorModel colaborador;
    
    @ManyToOne
    private EquipamentoModel equipamento;

    @Column(name="emp_vigente", nullable = false)
    private boolean isVigente;
    
    @Column(name="emp_finalidade", nullable = false, length = 200)
    private String finalidade;
    
    @ManyToOne
    private ColaboradorModel respEntrega;

    public void setVigente(int isVigente) {
        if (isVigente == 1) {
            this.isVigente = true;
        } else {
            this.isVigente = false;
        }
    }
}