package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import javax.swing.*;

public class MenuFiel {

    MenuDoacao menuDoacao = new MenuDoacao();

    /**
     * Inicia a Interface do Menu Fiel
     */
    public void UIMenuFiel() {
        loopUI:
        while (true) {
            String op = JOptionPane.showInputDialog(null, """
                    ===================================================
                    MENU FIEL
                    ===================================================
                    [1]: Realizar Doação
                    [2]: Ver todas as doações feitas pelo fiel
                    [3]: Voltar
                    ===================================================
                    Escolha uma Opção:
                    """);
            switch (op) {
                case "1":
                    menuDoacao.UIMenuDoacao();
                    break;
                case "2":
                    menuDoacao.UIMenuDoacoesFiel();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Voltando...");
                    break loopUI;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
            }
        }
    }
}
