package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.OcorrenciaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<OcorrenciaModel, Integer>{
    Page<OcorrenciaModel> findAll(Pageable pageable);
    Page<OcorrenciaModel> findAllByAtivo(Pageable pageable, String ativo);
}
