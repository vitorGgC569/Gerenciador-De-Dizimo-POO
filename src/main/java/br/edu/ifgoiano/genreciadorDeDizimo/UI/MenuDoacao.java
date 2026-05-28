package br.edu.ifgoiano.genreciadorDeDizimo.UI;

import javax.swing.*;

public class MenuDoacao {

    public void UIMenuDoacao() {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, """
                    Insira um valor em R$:
                    (0 - Voltar)
                    """);
            if (valor.equals("0")) break;
            if (!isDouble(valor)) { //Se não inserir um double
                JOptionPane.showMessageDialog(null, "ERRO: É necessário inserir um valor numérico!");
                continue;
            }
            Double valorDouble = Double.parseDouble(valor);
            String tipo = JOptionPane.showInputDialog(null, """
                    Insira o tipo de doação (Descrição):
                    (0 - Voltar)
                    """);
            if (tipo.equals("0")) break;

            //***registra a doação no banco de dados

            break; //finaliza e volta para o menu do fiel
        }
    }

    public void UIMenuDoacoesFiel() {
        //listar todas as doações do fiel logado
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
