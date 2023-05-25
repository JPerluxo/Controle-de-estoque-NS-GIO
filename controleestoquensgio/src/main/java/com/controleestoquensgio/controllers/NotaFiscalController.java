package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.notaFiscal.ListarNotaFiscalDto;
import com.controleestoquensgio.dtos.notaFiscal.NotaFiscalDto;
import com.controleestoquensgio.dtos.notaFiscal.VisualizarNotaFiscalDto;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.services.NotaFiscalService;
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
@RequestMapping(value = {"/notasFiscais"})
public class NotaFiscalController {

    @Autowired
    NotaFiscalService notaFiscalSvc;

    @PostMapping
    public String save(@Valid NotaFiscalDto notaFiscalDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("notaFiscalDto", notaFiscalDto);
            model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
            return "notaFiscal/cadastrarNotaFiscal";
        }

        var notaFiscalModel = new NotaFiscalModel();

        BeanUtils.copyProperties(notaFiscalDto, notaFiscalModel);

        notaFiscalModel.setData(new java.sql.Date(notaFiscalDto.getData().getTime()));

        var resultado = notaFiscalSvc.save(notaFiscalModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/notasFiscais";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = notaFiscalSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/notasFiscais";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid NotaFiscalDto notaFiscalDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("notaFiscalDto", notaFiscalDto);
            return "notaFiscal/atualizarNotaFiscal";
        }

        Optional<NotaFiscalModel> notaFiscalModelOptional = notaFiscalSvc.findById(id);

        if (notaFiscalModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.notaFiscalNaoEncontrada()
            );

            return "redirect:/notasFiscais";
        }

        var notaFiscalModel = notaFiscalModelOptional.get();

        BeanUtils.copyProperties(notaFiscalDto, notaFiscalModel);

        notaFiscalModel.setData(new java.sql.Date(notaFiscalDto.getData().getTime()));

        var resultado = notaFiscalSvc.update(notaFiscalModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/notasFiscais";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("notaFiscalDto", new NotaFiscalDto());
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));

        return "notaFiscal/cadastrarNotaFiscal";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<NotaFiscalModel> notaFiscalModelOptional = notaFiscalSvc.findById(id);

        if (notaFiscalModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/notasFiscais";
        }

        model.addAttribute("notaFiscalDto", new VisualizarNotaFiscalDto(notaFiscalModelOptional.get()));

        return "notaFiscal/atualizarNotaFiscal";
    }
}
