package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.dtos.TipoColaboradorDto;
import com.controleestoquensgio.services.TipoColaboradorService;

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
@RequestMapping(value = {"/controle-estoque/tipoColaborador"})
public class TipoColaboradorController extends ControllerFather{

    @Autowired
    TipoColaboradorService tipoColaboradorSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid TipoColaboradorDto tipoColaboradorDto){
        
        var tipoColaboradorModel = new TipoColaboradorModel();
        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoColaboradorSvc.save(tipoColaboradorModel));
    }

    @GetMapping
    public ResponseEntity<Page<TipoColaboradorModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(tipoColaboradorSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(id);
        if(!tipoColaboradorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de colaborador não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tipoColaboradorModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(id);
        if(!tipoColaboradorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de colaborador não encontrado");
        }
        tipoColaboradorSvc.delete(tipoColaboradorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de colaborador deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid TipoColaboradorDto tipoColaboradorDto){
        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(id);
        if(!tipoColaboradorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de colaborador não encontrado");
        }

        var tipoColaboradorModel = tipoColaboradorModelOptional.get();
        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);

        return ResponseEntity.status(HttpStatus.OK).body(tipoColaboradorSvc.save(tipoColaboradorModel));
    }
}
