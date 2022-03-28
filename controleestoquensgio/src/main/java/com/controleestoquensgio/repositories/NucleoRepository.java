package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.NucleoModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NucleoRepository extends JpaRepository<NucleoModel, Integer>{
    Page<NucleoModel> findAll(Pageable pageable);
}
