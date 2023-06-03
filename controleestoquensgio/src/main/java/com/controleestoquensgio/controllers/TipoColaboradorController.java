package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.tipoColaborador.ListarTipoColaboradorDto;
import com.controleestoquensgio.dtos.tipoColaborador.TipoColaboradorDto;
import com.controleestoquensgio.dtos.tipoColaborador.VisualizarTipoColaboradorDto;
import com.controleestoquensgio.dtos.tipoColaborador.FiltrarTipoColaboradorDto;
import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.services.TipoColaboradorService;
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
@RequestMapping(value = {"/tiposDeColaborador"})
public class TipoColaboradorController {

    @Autowired
    TipoColaboradorService tipoColaboradorSvc;

    @PostMapping
    public String save(@Valid TipoColaboradorDto tipoColaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoColaboradorDto", tipoColaboradorDto);
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoColaboradorDto::new));
            model.addAttribute("filtrarTipoColaboradorDto", new FiltrarTipoColaboradorDto());
            return "tipoColaborador/cadastrarTipoColaborador";
        }

        var tipoColaboradorModel = new TipoColaboradorModel();

        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);

        var resultado = tipoColaboradorSvc.save(tipoColaboradorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = tipoColaboradorSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid TipoColaboradorDto tipoColaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoColaboradorDto", tipoColaboradorDto);
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoColaboradorDto::new));
            return "tipoColaborador/atualizarTipoColaborador";
        }

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );

            return "redirect:/tiposDeColaborador";
        }

        var tipoColaboradorModel = tipoColaboradorModelOptional.get();

        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);

        var resultado = tipoColaboradorSvc.update(tipoColaboradorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoColaboradorDto::new));
        model.addAttribute("tipoColaboradorDto", new TipoColaboradorDto());
        model.addAttribute("filtrarTipoColaboradorDto", new FiltrarTipoColaboradorDto());

        return "tipoColaborador/cadastrarTipoColaborador";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );

            return "redirect:/tiposDeColaborador";
        }

        model.addAttribute("tipoColaboradorDto", new VisualizarTipoColaboradorDto(tipoColaboradorModelOptional.get()));

        return "tipoColaborador/atualizarTipoColaborador";
    }

    @PostMapping("/filtrar")
    public String filter(Pageable pageable, Model model, FiltrarTipoColaboradorDto filtrarTipoColaboradorDto) {

        model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAllByFilter(pageable, filtrarTipoColaboradorDto).map(ListarTipoColaboradorDto::new));
        model.addAttribute("tipoColaboradorDto", new TipoColaboradorDto());
        model.addAttribute("filtrarTipoColaboradorDto", new FiltrarTipoColaboradorDto());

        return "tipoColaborador/cadastrarTipoColaborador";
    }
}
