package com.controleestoquensgio.repositories;

import java.util.UUID;

import com.controleestoquensgio.models.LicencaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepository extends JpaRepository<LicencaModel, UUID>{
    Page<LicencaModel> findAll(Pageable pageable);
}
