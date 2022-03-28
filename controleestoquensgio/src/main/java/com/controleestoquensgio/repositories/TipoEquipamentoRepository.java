package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.TipoEquipamentoModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamentoModel, Integer>{
    Page<TipoEquipamentoModel> findAll(Pageable pageable);
}
