package com.controleestoquensgio.controllers;

import java.util.Optional;


import com.controleestoquensgio.dtos.licenca.LicencaDto;
import com.controleestoquensgio.dtos.licenca.ListarLicencaDto;
import com.controleestoquensgio.dtos.licenca.VisualizarLicencaDto;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.services.LicencaService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/licencas"})
public class LicencaController {

    @Autowired
    LicencaService licencaSvc;

    @PostMapping
    public String save(@Valid LicencaDto licencaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("licencaDto", licencaDto);
            model.addAttribute("listaDeLicencas", licencaSvc.findAll(pageable).map(ListarLicencaDto::new));
            return "licenca/cadastrarLicenca";
        }

        var licencaModel = new LicencaModel();

        BeanUtils.copyProperties(licencaDto, licencaModel);

        var resultado = licencaSvc.save(licencaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/licencas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = licencaSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/licencas";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid LicencaDto licencaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("licencaDto", licencaDto);
            model.addAttribute("listaDeLicencas", licencaSvc.findAll(pageable).map(ListarLicencaDto::new));
            return "licenca/atualizarLicenca";
        }

        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);

        if (licencaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.licencaNaoEncontrada()
            );

            return "redirect:/licencas";
        }

        var licencaModel = licencaModelOptional.get();

        BeanUtils.copyProperties(licencaDto, licencaModel);

        var resultado = licencaSvc.update(licencaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/licencas";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeLicencas", licencaSvc.findAll(pageable).map(ListarLicencaDto::new));
        model.addAttribute("licencaDto", new LicencaDto());

        return "licenca/cadastrarLicenca";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<LicencaModel> licencaModelOptional = licencaSvc.findById(id);

        if (licencaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/licencas";
        }

        model.addAttribute("licencaDto", new VisualizarLicencaDto(licencaModelOptional.get()));

        return "licenca/atualizarLicenca";
    }
}
