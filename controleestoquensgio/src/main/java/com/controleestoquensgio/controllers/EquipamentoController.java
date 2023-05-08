package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.ListarEquipamentosDto;
import com.controleestoquensgio.services.*;
import jakarta.validation.Valid;

import com.controleestoquensgio.dtos.EquipamentoDto;
import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.util.ErroOuSucesso;
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
@RequestMapping(value = {"/equipamentos"})
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoSvc;

    @Autowired
    TipoEquipamentoService tipoEquipamentoSvc;

    @Autowired
    NotaFiscalService notaFiscalSvc;

    @Autowired
    LocalizacaoService localizacaoSvc;

    @Autowired
    ContratoEquipamentoTerceiroService contratoEquipamentoTerceiroSvc;

    @PostMapping
    public String save(@Valid EquipamentoDto equipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("equipamentoDto", equipamentoDto);
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
            model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAll(pageable));
            model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAll(pageable));
            model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAll(pageable));
            model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAll(pageable));
            return "equipamento/cadastrarEquipamento";
        }

        var resultado = equipamentoSvc.save(equipamentoDto, new EquipamentoModel());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = equipamentoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid EquipamentoDto equipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("equipamentoDto", equipamentoDto);
            model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAll(pageable));
            model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAll(pageable));
            model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAll(pageable));
            model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAll(pageable));
            return "equipamento/atualizarEquipamento";
        }

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);

        if (equipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.equipamentoNaoEncontrado()
            );

            return "redirect:/equipamentos";
        }

        var resultado = equipamentoSvc.save(equipamentoDto, equipamentoModelOptional.get());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
        model.addAttribute("equipamentoDto", new EquipamentoDto());
        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAll(pageable));
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAll(pageable));
        model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAll(pageable));
        model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAll(pageable));

        return "equipamento/cadastrarEquipamento";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);

        if (equipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/equipamentos";
        }

        EquipamentoDto equipamentoDto = new EquipamentoDto();
        EquipamentoModel equipamentoModel = equipamentoModelOptional.get();

        BeanUtils.copyProperties(equipamentoModel, equipamentoDto);

        equipamentoDto.setTipoEquipamentoId(equipamentoModel.getTipoEquipamento().getId());
        equipamentoDto.setLocalizacaoId(equipamentoModel.getLocalizacao().getId());

        if (equipamentoModel.getNotaFiscal() != null) equipamentoDto.setNotaFiscalId(equipamentoModel.getNotaFiscal().getId());
        if (equipamentoModel.getContratoEquipamentoTerceiro() != null) equipamentoDto.setContratoEquipamentoTerceiroId(equipamentoModel.getContratoEquipamentoTerceiro().getId());

        model.addAttribute("equipamentoDto", equipamentoDto);
        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAll(pageable));
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAll(pageable));
        model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAll(pageable));
        model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAll(pageable));

        return "equipamento/atualizarEquipamento";
    }
}
