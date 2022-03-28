package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.NotaFiscalModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalModel, Integer>{
    Page<NotaFiscalModel> findAll(Pageable pageable);
}
