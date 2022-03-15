package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.dtos.LicencaDto;
import com.controleestoquensgio.services.LicencaService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/licenca", "/"})
public class LicencaController {

    final LicencaService licencaSvc;

    public LicencaController(LicencaService licencaSvc) {
        this.licencaSvc = licencaSvc;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid LicencaDto licencaDto){
        
        var licencaModel = new LicencaModel();
        BeanUtils.copyProperties(licencaDto, licencaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(licencaSvc.save(licencaModel));
    }

    @GetMapping
    public ResponseEntity<Page<LicencaModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(licencaSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);
        if(!licencaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(licencaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);
        if(!licencaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        licencaSvc.delete(licencaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("License deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid LicencaDto licencaDto){
        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);
        if(!licencaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }

        var licencaModel = licencaModelOptional.get();
        licencaModel.setDescricao(licencaDto.getDescricao());

        return ResponseEntity.status(HttpStatus.OK).body(licencaSvc.save(licencaModel));
    }
}
