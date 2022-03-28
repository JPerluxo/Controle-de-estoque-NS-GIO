package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.DiretoriaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretoriaRepository extends JpaRepository<DiretoriaModel, Integer>{
    Page<DiretoriaModel> findAll(Pageable pageable);
}
