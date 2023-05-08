package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.util.ErroOuSucesso;
import jakarta.validation.Valid;

import com.controleestoquensgio.dtos.LocalizacaoDto;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.services.LocalizacaoService;
import com.controleestoquensgio.util.Mensagens;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/localizacoes"})
public class LocalizacaoController {

    @Autowired
    LocalizacaoService localizacaoService;

    @PostMapping
    public String save(@Valid LocalizacaoDto localizacaoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("localizacaoDto", localizacaoDto);
            model.addAttribute("listaDeLocalizacoes", localizacaoService.findAll(pageable));
            return "localizacao/cadastrarLocalizacao";
        }

        var localizacaoModel = new LocalizacaoModel();

        BeanUtils.copyProperties(localizacaoDto, localizacaoModel);

        var resultado = localizacaoService.save(localizacaoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/localizacoes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = localizacaoService.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/localizacoes";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid LocalizacaoDto localizacaoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("localizacaoDto", localizacaoDto);
            model.addAttribute("listaDeLocalizacoes", localizacaoService.findAll(pageable));
            return "localizacao/atualizarLocalizacao";
        }

        Optional<LocalizacaoModel> localizacaoModelOptional = localizacaoService.findById(id);

        if (localizacaoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.localizacaoNaoEncontrada()
            );

            return "redirect:/localizacoes";
        }

        var localizacaoModel = localizacaoModelOptional.get();

        BeanUtils.copyProperties(localizacaoDto, localizacaoModel);

        var resultado = localizacaoService.update(localizacaoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/localizacoes";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<LocalizacaoModel> listaDeLocalizacoes = localizacaoService.findAll(pageable);
        LocalizacaoDto localizacaoDto = new LocalizacaoDto();

        model.addAttribute("listaDeLocalizacoes", listaDeLocalizacoes);
        model.addAttribute("localizacaoDto", localizacaoDto);

        return "localizacao/cadastrarLocalizacao";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<LocalizacaoModel> localizacaoModelOptional = localizacaoService.findById(id);

        if (localizacaoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.localizacaoNaoEncontrada()
            );

            return "redirect:/localizacoes";
        }

        model.addAttribute("localizacaoDto", localizacaoModelOptional.get());

        return "localizacao/atualizarLocalizacao";
    }
}
