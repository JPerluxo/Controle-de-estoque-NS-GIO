package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.emprestimo.FiltrarEmprestimoDto;
import com.controleestoquensgio.models.EmprestimoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmprestimoQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<EmprestimoModel> customQuery (FiltrarEmprestimoDto filtrarEmprestimoDto) {
        StringBuilder query = new StringBuilder("FROM EmprestimoModel WHERE");

        if (!filtrarEmprestimoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :emp_ativo");
        }

        if (filtrarEmprestimoDto.getColaboradorId() > 0) {
            query.append(" AND colaborador = :emp_col_id");
        }

        if (filtrarEmprestimoDto.getEquipamentoId() > 0) {
            query.append(" AND equipamento = :emp_eqp_id");
        }

        if (!filtrarEmprestimoDto.getFinalidade().isEmpty()) {
            query.append(" AND finalidade = :emp_finalidade");
        }

        if (filtrarEmprestimoDto.getRespEntregaId() > 0) {
            query.append(" AND respEntrega = :emp_col_resp_id");
        }

        TypedQuery<EmprestimoModel> typedQuery = em.createQuery(query.toString(), EmprestimoModel.class);

        if (!filtrarEmprestimoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("emp_ativo", filtrarEmprestimoDto.getAtivo());
        }

        if (filtrarEmprestimoDto.getColaboradorId() > 0) {
            typedQuery.setParameter("emp_col_id", filtrarEmprestimoDto.getColaborador());
        }

        if (filtrarEmprestimoDto.getEquipamentoId() > 0) {
            typedQuery.setParameter("emp_eqp_id", filtrarEmprestimoDto.getEquipamento());
        }

        if (!filtrarEmprestimoDto.getFinalidade().isEmpty()) {
            typedQuery.setParameter("emp_finalidade", filtrarEmprestimoDto.getFinalidade());
        }

        if (filtrarEmprestimoDto.getRespEntregaId() > 0) {
            typedQuery.setParameter("emp_col_resp_id", filtrarEmprestimoDto.getRespEntrega());
        }

        return typedQuery.getResultList();
    }

}
