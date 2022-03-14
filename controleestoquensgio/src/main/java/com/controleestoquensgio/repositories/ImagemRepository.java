package com.controleestoquensgio.repositories;

import java.util.UUID;

import com.controleestoquensgio.models.ImagemModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<ImagemModel, UUID>{
    Page<ImagemModel> findAll(Pageable pageable);
}
