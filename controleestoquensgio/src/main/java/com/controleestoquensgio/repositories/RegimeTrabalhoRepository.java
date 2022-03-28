package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.RegimeTrabalhoModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimeTrabalhoRepository extends JpaRepository<RegimeTrabalhoModel, Integer>{
    Page<RegimeTrabalhoModel> findAll(Pageable pageable);
}
