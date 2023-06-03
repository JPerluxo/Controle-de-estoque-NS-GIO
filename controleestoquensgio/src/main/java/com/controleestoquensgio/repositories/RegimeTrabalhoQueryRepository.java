package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.regimeTrabalho.FiltrarRegimeTrabalhoDto;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegimeTrabalhoQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<RegimeTrabalhoModel> customQuery (FiltrarRegimeTrabalhoDto filtrarRegimeTrabalhoDto) {
        StringBuilder query = new StringBuilder("FROM RegimeTrabalhoModel WHERE");

        if (!filtrarRegimeTrabalhoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :rgt_ativo");
        }

        if (!filtrarRegimeTrabalhoDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :rgt_descricao");
        }


        TypedQuery<RegimeTrabalhoModel> typedQuery = em.createQuery(query.toString(), RegimeTrabalhoModel.class);

        if (!filtrarRegimeTrabalhoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("rgt_ativo", filtrarRegimeTrabalhoDto.getAtivo());
        }

        if (!filtrarRegimeTrabalhoDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("rgt_descricao", filtrarRegimeTrabalhoDto.getDescricao());
        }

        return typedQuery.getResultList();
    }
}
