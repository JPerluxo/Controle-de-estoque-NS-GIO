package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.notaFiscal.FiltrarNotaFiscalDto;
import com.controleestoquensgio.models.NotaFiscalModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotaFiscalQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<NotaFiscalModel> customQuery (FiltrarNotaFiscalDto filtrarNotaFiscalDto) {
        StringBuilder query = new StringBuilder("FROM NotaFiscalModel WHERE");

        if (!filtrarNotaFiscalDto.getAtivo().isEmpty()) {
            query.append(" ativo = :nof_ativo");
        }

        if (!filtrarNotaFiscalDto.getNumero().isEmpty()) {
            query.append(" AND numero = :nof_numero");
        }

        if (filtrarNotaFiscalDto.getData() != null) {
            query.append(" AND data = :nof_dt");
        }

        TypedQuery<NotaFiscalModel> typedQuery = em.createQuery(query.toString(), NotaFiscalModel.class);

        if (!filtrarNotaFiscalDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("nof_ativo", filtrarNotaFiscalDto.getAtivo());
        }

        if (!filtrarNotaFiscalDto.getNumero().isEmpty()) {
            typedQuery.setParameter("nof_numero", filtrarNotaFiscalDto.getNumero());
        }

        if (filtrarNotaFiscalDto.getData() != null) {
            typedQuery.setParameter("nof_dt", filtrarNotaFiscalDto.getData());
        }

        return typedQuery.getResultList();
    }
}
