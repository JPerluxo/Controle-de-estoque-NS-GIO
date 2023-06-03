package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.tipoColaborador.FiltrarTipoColaboradorDto;
import com.controleestoquensgio.models.TipoColaboradorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoColaboradorQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<TipoColaboradorModel> customQuery (FiltrarTipoColaboradorDto filtrarTipoColaboradorDto) {
        StringBuilder query = new StringBuilder("FROM TipoColaboradorModel WHERE");

        if (!filtrarTipoColaboradorDto.getAtivo().isEmpty()) {
            query.append(" ativo = :tco_ativo");
        }

        if (!filtrarTipoColaboradorDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :tco_descricao");
        }

        TypedQuery<TipoColaboradorModel> typedQuery = em.createQuery(query.toString(), TipoColaboradorModel.class);

        if (!filtrarTipoColaboradorDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("tco_ativo", filtrarTipoColaboradorDto.getAtivo());
        }

        if (!filtrarTipoColaboradorDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("tco_descricao", filtrarTipoColaboradorDto.getDescricao());
        }

        return typedQuery.getResultList();
    }
}
