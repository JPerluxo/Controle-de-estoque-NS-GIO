package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.dtos.TipoAcessoDto;
import com.controleestoquensgio.services.TipoAcessoService;

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
@RequestMapping(value = {"/controle-estoque/tipoAcesso", "/"})
public class TipoAcessoController {

    @Autowired
    TipoAcessoService tipoAcessoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid TipoAcessoDto tipoAcessoDto){
        
        var tipoAcessoModel = new TipoAcessoModel();
        BeanUtils.copyProperties(tipoAcessoDto, tipoAcessoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoAcessoSvc.save(tipoAcessoModel));
    }

    @GetMapping
    public ResponseEntity<Page<TipoAcessoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(tipoAcessoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(id);
        if(!tipoAcessoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tipoAcessoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(id);
        if(!tipoAcessoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        tipoAcessoSvc.delete(tipoAcessoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("License deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid TipoAcessoDto tipoAcessoDto){
        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(id);
        if(!tipoAcessoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }

        var tipoAcessoModel = tipoAcessoModelOptional.get();

        return ResponseEntity.status(HttpStatus.OK).body(tipoAcessoSvc.save(tipoAcessoModel));
    }
}
