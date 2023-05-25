package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.ImagemModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<ImagemModel, Integer>{
    Page<ImagemModel> findAll(Pageable pageable);
    Page<ImagemModel> findAllByAtivo(Pageable pageable, String ativo);
}
