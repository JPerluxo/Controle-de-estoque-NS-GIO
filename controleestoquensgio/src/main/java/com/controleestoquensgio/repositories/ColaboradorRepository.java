package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.ColaboradorModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Integer>{
    Page<ColaboradorModel> findAll(Pageable pageable);
    Page<ColaboradorModel> findAllByAtivo(Pageable pageable, String ativo);
}
