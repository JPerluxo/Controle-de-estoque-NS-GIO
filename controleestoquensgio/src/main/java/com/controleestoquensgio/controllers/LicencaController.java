package com.controleestoquensgio.controllers;

import java.util.Optional;
import java.util.UUID;

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
@RequestMapping(value = {"/controle-estoque", "/"})
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
    public ResponseEntity<Page<LicencaModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "lic_id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(licencaSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id){
        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);
        if(!licencaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(licencaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);
        if(!licencaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        licencaSvc.delete(licencaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("License deleted successfully");
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
        }

        var parkingSpotModel = parkingSpotModelOptional.get();
        parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
        parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
        parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
        parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
        parkingSpotModel.setApartment(parkingSpotDto.getApartment());
        parkingSpotModel.setBlock(parkingSpotDto.getBlock());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }*/
}
