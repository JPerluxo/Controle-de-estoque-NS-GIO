package com.controleestoquensgio.repositories;

import com.controleestoquensgio.dtos.licenca.FiltrarLicencaDto;
import com.controleestoquensgio.models.LicencaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LicencaQueryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<LicencaModel> customQuery (FiltrarLicencaDto filtrarLicencaDto) {
        StringBuilder query = new StringBuilder("FROM LicencaModel WHERE");

        if (!filtrarLicencaDto.getAtivo().isEmpty()) {
            query.append(" ativo = :lic_ativo");
        }

        if (!filtrarLicencaDto.getDescricao().isEmpty()) {
            query.append(" AND descricao = :lic_descricao");
        }

        TypedQuery<LicencaModel> typedQuery = em.createQuery(query.toString(), LicencaModel.class);

        if (!filtrarLicencaDto.getAtivo().isEmpty()) {
            typedQuery.setParameter("lic_ativo", filtrarLicencaDto.getAtivo());
        }

        if (!filtrarLicencaDto.getDescricao().isEmpty()) {
            typedQuery.setParameter("lic_descricao", filtrarLicencaDto.getDescricao());
        }

        return typedQuery.getResultList();
    }

}
