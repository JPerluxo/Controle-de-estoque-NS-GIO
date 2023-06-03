package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.setor.FiltrarSetorDto;
import com.controleestoquensgio.models.SetorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SetorQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<SetorModel> customQuery (FiltrarSetorDto filtrarSetorDto) {
        StringBuilder query = new StringBuilder("FROM SetorModel WHERE");

        if (!filtrarSetorDto.getAtivo().isEmpty()) {
            query.append(" ativo = :set_ativo");
        }

        if (!filtrarSetorDto.getSigla().isEmpty()) {
            query.append(" AND sigla = :set_sigla");
        }

        if (!filtrarSetorDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :set_descricao");
        }

        if (filtrarSetorDto.getResponsavelId() > 0) {
            query.append(" AND responsavel = :set_col_id");
        }

        if (!filtrarSetorDto.getNivel().isEmpty()) {
            query.append(" AND nivel = :set_nivel");
        }

        TypedQuery<SetorModel> typedQuery = em.createQuery(query.toString(), SetorModel.class);

        if (!filtrarSetorDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("set_ativo", filtrarSetorDto.getAtivo());
        }

        if (!filtrarSetorDto.getSigla().isEmpty()) {
            typedQuery.setParameter("set_sigla", filtrarSetorDto.getSigla());
        }

        if (!filtrarSetorDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("set_descricao", filtrarSetorDto.getDescricao());
        }

        if (filtrarSetorDto.getResponsavelId() > 0) {
            typedQuery.setParameter("set_col_id", filtrarSetorDto.getResponsavel());
        }

        if (!filtrarSetorDto.getNivel().isEmpty()) {
            typedQuery.setParameter("set_nivel", filtrarSetorDto.getNivel());
        }


        return typedQuery.getResultList();
    }
    
}
