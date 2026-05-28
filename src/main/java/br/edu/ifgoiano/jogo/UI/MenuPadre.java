package br.edu.ifgoiano.jogo.UI;

import javax.swing.*;

public class MenuPadre {

    public void UIMenuPadre() {
        loopUI:
        while (true) {
            String op = JOptionPane.showInputDialog(null, """
                    ===================================================
                    MENU PADRE
                    ===================================================
                    [1]: Ver todas as doações
                    [2]: Voltar
                    ===================================================
                    Escolha uma Opção:
                    """);

            switch (op) {
                case "1":
                    //listar todas as doações do banco de dados
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Voltando...");
                    break loopUI;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
            }
        }
    }
}
