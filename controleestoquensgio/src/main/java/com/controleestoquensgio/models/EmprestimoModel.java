package com.controleestoquensgio.models;

import java.time.LocalDate;

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
    private LocalDate dataDisponibilizacao;
    
    @Column(name="emp_dtDevolucao", nullable = false)
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name="emp_col_id")
    private ColaboradorModel colaborador;
    
    @ManyToOne
    @JoinColumn(name="emp_eqp_id")
    private EquipamentoModel equipamento;

    @Column(name="emp_ativo", nullable = false, length = 4)
    private String ativo;
    
    @Column(name="emp_finalidade", nullable = false, length = 200)
    private String finalidade;
    
    @ManyToOne
    @JoinColumn(name="emp_col_resp_id")
    private ColaboradorModel respEntrega;

    public EmprestimoModel() {
        this.ativo = SimOuNao.SIM.name();
    }
}