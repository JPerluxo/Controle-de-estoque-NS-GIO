package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.GerenciaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenciaRepository extends JpaRepository<GerenciaModel, Integer>{
    Page<GerenciaModel> findAll(Pageable pageable);
}
