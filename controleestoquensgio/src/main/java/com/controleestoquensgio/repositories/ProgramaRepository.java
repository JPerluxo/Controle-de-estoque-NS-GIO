package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.ProgramaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<ProgramaModel, Integer>{
    Page<ProgramaModel> findAll(Pageable pageable);
}
