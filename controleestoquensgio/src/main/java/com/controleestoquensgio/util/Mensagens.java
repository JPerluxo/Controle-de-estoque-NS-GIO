package com.controleestoquensgio.util;

public class Mensagens {
    public static String operacaoBemSucedida() { return "Operação bem-sucedida"; }
    public static String operacaoBemSucedidaTipoDeMensagem() { return ErroOuSucesso.SUCESSO.name(); }

    public static String tipoDeEquipamentoNaoEncontrado() { return "Tipo de equipamento não encontrado!"; }
    public static String tipoDeEquipamentoNaoEncontradoTipoDeMensagem() { return ErroOuSucesso.ERRO.name(); }
}
