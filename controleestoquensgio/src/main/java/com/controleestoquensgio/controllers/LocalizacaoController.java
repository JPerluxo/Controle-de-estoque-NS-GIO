package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.localizacao.ListarLocalizacaoDto;
import com.controleestoquensgio.dtos.localizacao.LocalizacaoDto;
import com.controleestoquensgio.dtos.localizacao.VisualizarLocalizacaoDto;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.services.LocalizacaoService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = {"/localizacoes"})
public class LocalizacaoController {

    @Autowired
    LocalizacaoService localizacaoService;

    @PostMapping
    public String save(@Valid LocalizacaoDto localizacaoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("localizacaoDto", localizacaoDto);
            model.addAttribute("listaDeLocalizacoes", localizacaoService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
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
            model.addAttribute("listaDeLocalizacoes", localizacaoService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
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

        model.addAttribute("listaDeLocalizacoes", localizacaoService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
        model.addAttribute("localizacaoDto", new LocalizacaoDto());

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

        model.addAttribute("localizacaoDto", new VisualizarLocalizacaoDto(localizacaoModelOptional.get()));

        return "localizacao/atualizarLocalizacao";
    }
}
