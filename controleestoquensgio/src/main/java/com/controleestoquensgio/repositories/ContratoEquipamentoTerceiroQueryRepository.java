package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.FiltrarContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratoEquipamentoTerceiroQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<ContratoEquipamentoTerceiroModel> customQuery (FiltrarContratoEquipamentoTerceiroDto filtrarContratoEquipamentoTerceiroDto) {
        StringBuilder query = new StringBuilder("FROM ContratoEquipamentoTerceiroModel WHERE");

        if (!filtrarContratoEquipamentoTerceiroDto.getAtivo().isEmpty()) {
            query.append(" ativo = :cet_ativo");
        }

        if (!filtrarContratoEquipamentoTerceiroDto.getFornecedor().isEmpty()) {
            query.append(" AND fornecedor = :cet_fornecedor");
        }

        TypedQuery<ContratoEquipamentoTerceiroModel> typedQuery = em.createQuery(query.toString(), ContratoEquipamentoTerceiroModel.class);

        if (!filtrarContratoEquipamentoTerceiroDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("cet_ativo", filtrarContratoEquipamentoTerceiroDto.getAtivo());
        }

        if (!filtrarContratoEquipamentoTerceiroDto.getFornecedor().isEmpty()) {
            typedQuery.setParameter("cet_fornecedor", filtrarContratoEquipamentoTerceiroDto.getFornecedor());
        }

        return typedQuery.getResultList();
    }
}
