package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.TipoColaboradorModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoColaboradorRepository extends JpaRepository<TipoColaboradorModel, Integer>{
    Page<TipoColaboradorModel> findAll(Pageable pageable);
    Page<TipoColaboradorModel> findAllByAtivo(Pageable pageable, String ativo);
}
