package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.tipoEquipamento.FiltrarTipoEquipamentoDto;
import com.controleestoquensgio.models.TipoEquipamentoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoEquipamentoQueryRepository {

    @PersistenceContext
    private EntityManager em;

    public List<TipoEquipamentoModel> customQuery (FiltrarTipoEquipamentoDto filtrarTipoEquipamentoDto) {
        StringBuilder query = new StringBuilder("FROM TipoEquipamentoModel WHERE");

        if (!filtrarTipoEquipamentoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :teq_ativo");
        }

        if (!filtrarTipoEquipamentoDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :teq_descricao");
        }

        if (!filtrarTipoEquipamentoDto.getMarca().isEmpty()) {
            query.append(" AND marca = :teq_marca");
        }

        if (!filtrarTipoEquipamentoDto.getModelo().isEmpty()) {
            query.append(" AND modelo = :teq_modelo");
        }

        if (!filtrarTipoEquipamentoDto.getFornecedor().isEmpty()) {
            query.append(" AND fornecedor = :teq_fornecedor");
        }

        if (!filtrarTipoEquipamentoDto.getPolegadas().isEmpty()) {
            query.append(" AND polegadas = :teq_polegadas");
        }

        TypedQuery<TipoEquipamentoModel> typedQuery = em.createQuery(query.toString(), TipoEquipamentoModel.class);

        if (!filtrarTipoEquipamentoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("teq_ativo", filtrarTipoEquipamentoDto.getAtivo());
        }

        if (!filtrarTipoEquipamentoDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("teq_descricao", filtrarTipoEquipamentoDto.getDescricao());
        }

        if (!filtrarTipoEquipamentoDto.getMarca().isEmpty()) {
            typedQuery.setParameter("teq_marca", filtrarTipoEquipamentoDto.getMarca());
        }

        if (!filtrarTipoEquipamentoDto.getModelo().isEmpty()) {
            typedQuery.setParameter("teq_modelo", filtrarTipoEquipamentoDto.getModelo());
        }

        if (!filtrarTipoEquipamentoDto.getFornecedor().isEmpty()) {
            typedQuery.setParameter("teq_fornecedor", filtrarTipoEquipamentoDto.getFornecedor());
        }

        if (!filtrarTipoEquipamentoDto.getPolegadas().isEmpty()) {
            typedQuery.setParameter("teq_polegadas", filtrarTipoEquipamentoDto.getPolegadas());
        }

        return typedQuery.getResultList();
    }
}
