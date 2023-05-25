package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.LocalizacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<LocalizacaoModel, Integer>{
    Page<LocalizacaoModel> findAll(Pageable pageable);
    Page<LocalizacaoModel> findAllByAtivo(Pageable pageable, String ativo);
}
