package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.localizacao.FiltrarLocalizacaoDto;
import com.controleestoquensgio.models.LocalizacaoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalizacaoQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<LocalizacaoModel> customQuery (FiltrarLocalizacaoDto filtrarLocalizacaoDto) {
        StringBuilder query = new StringBuilder("FROM LocalizacaoModel WHERE");

        if (!filtrarLocalizacaoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :loc_ativo");
        }

        if (!filtrarLocalizacaoDto.getPredio().isEmpty()) {
            query.append(" AND predio = :loc_predio");
        }

        if (!filtrarLocalizacaoDto.getAndar().isEmpty()) {
            query.append(" AND andar = :loc_andar");
        }

        if (!filtrarLocalizacaoDto.getLado().isEmpty()) {
            query.append(" AND lado = :loc_lado");
        }

        if (!filtrarLocalizacaoDto.getReferencia().isEmpty()) {
            query.append(" AND referencia = :loc_referencia");
        }

        TypedQuery<LocalizacaoModel> typedQuery = em.createQuery(query.toString(), LocalizacaoModel.class);

        if (!filtrarLocalizacaoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("loc_ativo", filtrarLocalizacaoDto.getAtivo());
        }

        if (!filtrarLocalizacaoDto.getPredio().isEmpty()) {
            typedQuery.setParameter("loc_predio", filtrarLocalizacaoDto.getPredio());
        }

        if (!filtrarLocalizacaoDto.getAndar().isEmpty()) {
            typedQuery.setParameter("loc_andar", filtrarLocalizacaoDto.getAndar());
        }

        if (!filtrarLocalizacaoDto.getLado().isEmpty()) {
            typedQuery.setParameter("loc_lado", filtrarLocalizacaoDto.getLado());
        }

        if (!filtrarLocalizacaoDto.getLado().isEmpty()) {
            typedQuery.setParameter("loc_referencia", filtrarLocalizacaoDto.getLado());
        }

        return typedQuery.getResultList();
    }

}
