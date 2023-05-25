package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoEquipamentoTerceiroRepository extends JpaRepository<ContratoEquipamentoTerceiroModel, Integer>{
    Page<ContratoEquipamentoTerceiroModel> findAll(Pageable pageable);
    Page<ContratoEquipamentoTerceiroModel> findAllByAtivo(Pageable pageable, String ativo);
}
