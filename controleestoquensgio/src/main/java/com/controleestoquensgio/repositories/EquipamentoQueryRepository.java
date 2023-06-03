package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.equipamento.FiltrarEquipamentoDto;
import com.controleestoquensgio.models.EquipamentoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipamentoQueryRepository{
    @PersistenceContext
    private EntityManager em;

    public List<EquipamentoModel> customQuery (FiltrarEquipamentoDto filtrarEquipamentoDto) {
        StringBuilder query = new StringBuilder("FROM EquipamentoModel WHERE");

        if (!filtrarEquipamentoDto.getAtivo().isEmpty()) {
            query.append(" ativo = :eqp_ativo");
        }

        if (!filtrarEquipamentoDto.getSerial().isEmpty()) {
            query.append(" AND serial = :eqp_serial");
        }

        if (!filtrarEquipamentoDto.getNumPatrimonio().isEmpty()) {
            query.append(" AND numPatrimonio = :eqp_patrimonio");
        }

        if (!filtrarEquipamentoDto.getObservacao().isEmpty()) {
            query.append(" AND observacao = :eqp_observacao");
        }

        if (filtrarEquipamentoDto.getTipoEquipamentoId() > 0) {
            query.append(" AND tipoEquipamento = :eqp_teq_id");
        }

        if (filtrarEquipamentoDto.getNotaFiscalId() > 0) {
            query.append(" AND notaFiscal = :eqp_nof_id");
        }

        if (filtrarEquipamentoDto.getLocalizacaoId() > 0) {
            query.append(" AND localizacao = :eqp_loc_id");
        }

        if (filtrarEquipamentoDto.getContratoEquipamentoTerceiroId() > 0) {
            query.append(" AND contratoEquipamentoTerceiro = :eqp_cet_id");
        }

        TypedQuery<EquipamentoModel> typedQuery = em.createQuery(query.toString(), EquipamentoModel.class);

        if (!filtrarEquipamentoDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("eqp_ativo", filtrarEquipamentoDto.getAtivo());
        }

        if (!filtrarEquipamentoDto.getSerial().isEmpty()) {
            typedQuery.setParameter("eqp_serial", filtrarEquipamentoDto.getSerial());
        }

        if (!filtrarEquipamentoDto.getNumPatrimonio().isEmpty()) {
            typedQuery.setParameter("eqp_patrimonio", filtrarEquipamentoDto.getNumPatrimonio());
        }

        if (!filtrarEquipamentoDto.getObservacao().isEmpty()) {
            typedQuery.setParameter("eqp_observacao", filtrarEquipamentoDto.getObservacao());
        }

        if (filtrarEquipamentoDto.getTipoEquipamentoId() > 0) {
            typedQuery.setParameter("eqp_teq_id", filtrarEquipamentoDto.getTipoEquipamento());
        }

        if (filtrarEquipamentoDto.getNotaFiscalId() > 0) {
            typedQuery.setParameter("eqp_nof_id", filtrarEquipamentoDto.getNotaFiscal());
        }

        if (filtrarEquipamentoDto.getLocalizacaoId() > 0) {
            typedQuery.setParameter("eqp_loc_id", filtrarEquipamentoDto.getLocalizacao());
        }

        if (filtrarEquipamentoDto.getContratoEquipamentoTerceiroId() > 0) {
            typedQuery.setParameter("eqp_cet_id", filtrarEquipamentoDto.getContratoEquipamentoTerceiro());
        }

        return typedQuery.getResultList();
    }
}
