package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.EquipamentoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Integer>{
    Page<EquipamentoModel> findAll(Pageable pageable);
    Page<EquipamentoModel> findAllByAtivo(Pageable pageable, String ativo);
}
