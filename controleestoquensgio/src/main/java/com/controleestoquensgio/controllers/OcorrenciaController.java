package com.controleestoquensgio.controllers;

import java.util.Optional;


import jakarta.validation.Valid;
import com.controleestoquensgio.models.OcorrenciaModel;
import com.controleestoquensgio.dtos.OcorrenciaDto;
import com.controleestoquensgio.services.OcorrenciaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/ocorrencia"})
public class OcorrenciaController extends ControllerFather{

    @Autowired
    OcorrenciaService ocorrenciaSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid OcorrenciaDto ocorrenciaDto){
        var ocorrenciaModel = new OcorrenciaModel();
        BeanUtils.copyProperties(ocorrenciaDto, ocorrenciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaSvc.save(ocorrenciaModel));
    }

    @GetMapping
    public ResponseEntity<Page<OcorrenciaModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(ocorrenciaSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaSvc.findById(id);
        if(!ocorrenciaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ocorrenciaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaSvc.findById(id);
        if(!ocorrenciaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada");
        }
        ocorrenciaSvc.delete(ocorrenciaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ocorrência deletada com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid OcorrenciaDto ocorrenciaDto){
        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaSvc.findById(id);
        if(!ocorrenciaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada");
        }

        var ocorrenciaModel = ocorrenciaModelOptional.get();

        return ResponseEntity.status(HttpStatus.OK).body(ocorrenciaSvc.save(ocorrenciaModel));
    }
}
