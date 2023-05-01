package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.RegimeTrabalhoDto;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.services.RegimeTrabalhoService;
import com.controleestoquensgio.util.Mensagens;

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
public class RegimeTrabalhoController extends ControllerFather{

    @Autowired
    RegimeTrabalhoService regimeTrabalhoSvc;

    @PostMapping
    public String save(@Valid RegimeTrabalhoDto regimeTrabalhoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("regimeTrabalhoDto", regimeTrabalhoDto);
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));
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
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));
            return "regimeTrabalho/atualizarRegimeTrabalho";
        }

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);

        if (regimeTrabalhoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    Mensagens.regimeDeTrabalhoNaoEncontrado(),
                    Mensagens.regimeDeTrabalhoNaoEncontradoTipoDeMensagem()
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

        Iterable<RegimeTrabalhoModel> listaDeRegimesDeTrabalho = regimeTrabalhoSvc.findAll(pageable);
        RegimeTrabalhoDto regimeTrabalhoDto = new RegimeTrabalhoDto();

        model.addAttribute("listaDeRegimesDeTrabalho", listaDeRegimesDeTrabalho);
        model.addAttribute("regimeTrabalhoDto", regimeTrabalhoDto);

        return "regimeTrabalho/cadastrarRegimeTrabalho";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);

        if (regimeTrabalhoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    Mensagens.regimeDeTrabalhoNaoEncontrado(),
                    Mensagens.regimeDeTrabalhoNaoEncontradoTipoDeMensagem()
            );

            return "redirect:/regimesDeTrabalho";
        }

        model.addAttribute("regimeTrabalhoDto", regimeTrabalhoModelOptional.get());

        return "regimeTrabalho/atualizarRegimeTrabalho";
    }
}
