package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.PresidenciaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidenciaRepository extends JpaRepository<PresidenciaModel, Integer>{
    Page<PresidenciaModel> findAll(Pageable pageable);
}
