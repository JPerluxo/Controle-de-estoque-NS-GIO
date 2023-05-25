package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.regimeTrabalho.ListarRegimeTrabalhoDto;
import com.controleestoquensgio.dtos.regimeTrabalho.RegimeTrabalhoDto;
import com.controleestoquensgio.dtos.regimeTrabalho.VisualizarRegimeTrabalhoDto;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.services.RegimeTrabalhoService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;

import com.controleestoquensgio.util.SimOuNao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = {"regimesDeTrabalho"})
public class RegimeTrabalhoController {

    @Autowired
    RegimeTrabalhoService regimeTrabalhoSvc;

    @PostMapping
    public String save(@Valid RegimeTrabalhoDto regimeTrabalhoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("regimeTrabalhoDto", regimeTrabalhoDto);
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarRegimeTrabalhoDto::new));
            return "regimeTrabalho/cadastrarRegimeTrabalho";
        }

        var regimeTrabalhoModel = new RegimeTrabalhoModel();

        BeanUtils.copyProperties(regimeTrabalhoDto, regimeTrabalhoModel);

        var resultado = regimeTrabalhoSvc.save(regimeTrabalhoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/regimesDeTrabalho";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = regimeTrabalhoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/regimesDeTrabalho";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid RegimeTrabalhoDto regimeTrabalhoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("regimeTrabalhoDto", regimeTrabalhoDto);
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarRegimeTrabalhoDto::new));
            return "regimeTrabalho/atualizarRegimeTrabalho";
        }

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);

        if (regimeTrabalhoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.regimeDeTrabalhoNaoEncontrado()
            );

            return "redirect:/regimesDeTrabalho";
        }

        var regimeTrabalhoModel = regimeTrabalhoModelOptional.get();

        BeanUtils.copyProperties(regimeTrabalhoDto, regimeTrabalhoModel);

        var resultado = regimeTrabalhoSvc.update(regimeTrabalhoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/regimesDeTrabalho";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarRegimeTrabalhoDto::new));
        model.addAttribute("regimeTrabalhoDto", new RegimeTrabalhoDto());

        return "regimeTrabalho/cadastrarRegimeTrabalho";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);

        if (regimeTrabalhoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.regimeDeTrabalhoNaoEncontrado()
            );

            return "redirect:/regimesDeTrabalho";
        }

        model.addAttribute("regimeTrabalhoDto", new VisualizarRegimeTrabalhoDto(regimeTrabalhoModelOptional.get()));

        return "regimeTrabalho/atualizarRegimeTrabalho";
    }
}
