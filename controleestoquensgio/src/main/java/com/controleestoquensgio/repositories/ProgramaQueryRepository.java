package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.programa.FiltrarProgramaDto;
import com.controleestoquensgio.models.ProgramaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramaQueryRepository{
    @PersistenceContext
    private EntityManager em;

    public List<ProgramaModel> customQuery (FiltrarProgramaDto filtrarProgramaDto) {
        StringBuilder query = new StringBuilder("FROM ProgramaModel WHERE");

        if (!filtrarProgramaDto.getAtivo().isEmpty()) {
            query.append(" ativo = :prg_ativo");
        }

        if (!filtrarProgramaDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :prg_descricao");
        }

        if (!filtrarProgramaDto.getObservacao().isEmpty()) {
            query.append(" AND observacao = :prg_observacao");
        }

        if (filtrarProgramaDto.getLicencaId() > 0 ) {
            query.append(" AND licenca = :prg_lic_id");
        }
        
        TypedQuery<ProgramaModel> typedQuery = em.createQuery(query.toString(), ProgramaModel.class);

        if (!filtrarProgramaDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("prg_ativo", filtrarProgramaDto.getAtivo());
        }

        if (!filtrarProgramaDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("prg_descricao", filtrarProgramaDto.getDescricao());
        }

        if (!filtrarProgramaDto.getObservacao().isEmpty()) {
            typedQuery.setParameter("prg_observacao", filtrarProgramaDto.getObservacao());
        }

        if (filtrarProgramaDto.getLicencaId() > 0 ) {
            typedQuery.setParameter("prg_lic_id", filtrarProgramaDto.getLicencaModel());
        }
        
        return typedQuery.getResultList();
    }
}
