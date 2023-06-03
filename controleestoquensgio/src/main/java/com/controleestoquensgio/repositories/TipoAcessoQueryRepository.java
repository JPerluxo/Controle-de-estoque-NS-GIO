package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.tipoAcesso.FiltrarTipoAcessoDto;
import com.controleestoquensgio.models.TipoAcessoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoAcessoQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<TipoAcessoModel> customQuery (FiltrarTipoAcessoDto filtrarTipoAcessoDto) {
        StringBuilder query = new StringBuilder("FROM TipoAcessoModel WHERE");

        if (!filtrarTipoAcessoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :tac_ativo");
        }

        if (!filtrarTipoAcessoDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :tac_descricao");
        }

        TypedQuery<TipoAcessoModel> typedQuery = em.createQuery(query.toString(), TipoAcessoModel.class);

        if (!filtrarTipoAcessoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("tac_ativo", filtrarTipoAcessoDto.getAtivo());
        }

        if (!filtrarTipoAcessoDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("tac_descricao", filtrarTipoAcessoDto.getDescricao());
        }

        return typedQuery.getResultList();
    }
}
