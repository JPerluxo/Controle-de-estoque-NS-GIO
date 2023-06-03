package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.colaborador.FiltrarColaboradorDto;
import com.controleestoquensgio.models.ColaboradorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColaboradorQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<ColaboradorModel> customQuery (FiltrarColaboradorDto filtrarColaboradorDto) {
        StringBuilder query = new StringBuilder("FROM ColaboradorModel WHERE");

        if (!filtrarColaboradorDto.getAtivo().isEmpty()) {
            query.append(" ativo = :col_ativo");
        }

        if (!filtrarColaboradorDto.getRf().isEmpty()) {
            query.append(" AND rf = :col_rf");
        }

        if (!filtrarColaboradorDto.getNome().isEmpty()) {
            query.append(" AND nome = :col_nome");
        }

        if (filtrarColaboradorDto.getImagemId() > 0) {
            query.append(" AND imagem = :col_img_id");
        }

        if (filtrarColaboradorDto.getTipoAcessoId() > 0) {
            query.append(" AND tipoAcesso = :col_tac_id");
        }

        if (filtrarColaboradorDto.getTipoColaboradorId() > 0) {
            query.append(" AND tipoColaborador = :col_tco_id");
        }

        if (filtrarColaboradorDto.getRegimeTrabalhoId() > 0) {
            query.append(" AND regimeTrabalho = :col_rgt_id");
        }

        if (filtrarColaboradorDto.getPresidenciaId() > 0) {
            query.append(" AND presidencia = :col_pre_set_id");
        }

        if (filtrarColaboradorDto.getDiretoriaId() > 0) {
            query.append(" AND diretoria = :col_dir_set_id");
        }

        if (filtrarColaboradorDto.getGerenciaId() > 0) {
            query.append(" AND gerencia = :col_ger_set_id");
        }

        if (filtrarColaboradorDto.getNucleoId() > 0) {
            query.append(" AND nucleo = :col_nuc_set_id");
        }

        TypedQuery<ColaboradorModel> typedQuery = em.createQuery(query.toString(), ColaboradorModel.class);

        if (!filtrarColaboradorDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("col_ativo", filtrarColaboradorDto.getAtivo());
        }

        if (!filtrarColaboradorDto.getRf().isEmpty()) {
            typedQuery.setParameter("col_rf", filtrarColaboradorDto.getRf());
        }

        if (!filtrarColaboradorDto.getNome().isEmpty()) {
            typedQuery.setParameter("col_nome", filtrarColaboradorDto.getNome());
        }

        if (filtrarColaboradorDto.getImagemId() > 0) {
            typedQuery.setParameter("col_img_id", filtrarColaboradorDto.getImagem());
        }

        if (filtrarColaboradorDto.getTipoAcessoId() > 0) {
            typedQuery.setParameter("col_tac_id", filtrarColaboradorDto.getTipoAcesso());
        }

        if (filtrarColaboradorDto.getTipoColaboradorId() > 0) {
            typedQuery.setParameter("col_tco_id", filtrarColaboradorDto.getTipoColaborador());
        }

        if (filtrarColaboradorDto.getRegimeTrabalhoId() > 0) {
            typedQuery.setParameter("col_rgt_id", filtrarColaboradorDto.getRegimeTrabalho());
        }

        if (filtrarColaboradorDto.getPresidenciaId() > 0) {
            typedQuery.setParameter("col_pre_set_id", filtrarColaboradorDto.getPresidencia());
        }

        if (filtrarColaboradorDto.getDiretoriaId() > 0) {
            typedQuery.setParameter("col_dir_set_id", filtrarColaboradorDto.getDiretoria());
        }

        if (filtrarColaboradorDto.getGerenciaId() > 0) {
            typedQuery.setParameter("col_ger_set_id", filtrarColaboradorDto.getGerencia());
        }

        if (filtrarColaboradorDto.getNucleoId() > 0) {
            query.append(" AND nucleo = :col_nuc_set_id");
            typedQuery.setParameter("col_nuc_set_id", filtrarColaboradorDto.getNucleo());
        }

        return typedQuery.getResultList();
    }
}
