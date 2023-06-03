package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.ocorrencia.FiltrarOcorrenciaDto;
import com.controleestoquensgio.models.OcorrenciaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OcorrenciaQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<OcorrenciaModel> customQuery (FiltrarOcorrenciaDto filtrarOcorrenciaDto) {
        StringBuilder query = new StringBuilder("FROM OcorrenciaModel WHERE");

        if (!filtrarOcorrenciaDto.getAtivo().isEmpty()) {
            query.append(" ativo = :oco_ativo");
        }

        if (!filtrarOcorrenciaDto.getOs().isEmpty()) {
            query.append(" AND os = :oco_os");
        }

        if (!filtrarOcorrenciaDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :oco_descricao");
        }

        if (filtrarOcorrenciaDto.getEquipamentoId() > 0) {
            query.append(" AND equipamento = :oco_eqp_id");
        }

        if (filtrarOcorrenciaDto.getColaboradorId() > 0) {
            query.append(" AND colaborador = :oco_col_id");
        }

        TypedQuery<OcorrenciaModel> typedQuery = em.createQuery(query.toString(), OcorrenciaModel.class);

        if (!filtrarOcorrenciaDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("oco_ativo", filtrarOcorrenciaDto.getAtivo());
        }

        if (!filtrarOcorrenciaDto.getOs().isEmpty()) {
            typedQuery.setParameter("oco_os", filtrarOcorrenciaDto.getOs());
        }

        if (!filtrarOcorrenciaDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("oco_descricao", filtrarOcorrenciaDto.getDescricao());
        }

        if (filtrarOcorrenciaDto.getEquipamentoId() > 0) {
            typedQuery.setParameter("oco_eqp_id", filtrarOcorrenciaDto.getEquipamento());
        }

        if (filtrarOcorrenciaDto.getColaboradorId() > 0) {
            typedQuery.setParameter("oco_col_id", filtrarOcorrenciaDto.getColaborador());
        }

        return typedQuery.getResultList();
    }
}
