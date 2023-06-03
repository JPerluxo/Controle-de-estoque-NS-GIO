package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.imagem.FiltrarImagemDto;
import com.controleestoquensgio.models.ImagemModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImagemQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<ImagemModel> customQuery (FiltrarImagemDto filtrarImagemDto) {
        StringBuilder query = new StringBuilder("FROM ImagemModel WHERE");

        if (!filtrarImagemDto.getAtivo().isEmpty()) {
            query.append(" ativo = :img_ativo");
        }

        if (!filtrarImagemDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :img_descricao");
        }

        TypedQuery<ImagemModel> typedQuery = em.createQuery(query.toString(), ImagemModel.class);

        if (!filtrarImagemDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("img_ativo", filtrarImagemDto.getAtivo());
        }

        if (!filtrarImagemDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("img_descricao", filtrarImagemDto.getDescricao());
        }

        return typedQuery.getResultList();
    }
}
