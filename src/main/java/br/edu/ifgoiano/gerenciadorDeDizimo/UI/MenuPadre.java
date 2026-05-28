package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;

import javax.swing.*;

public class MenuPadre {

    /**
     * Inicia a Interface do Menu Padre
     */
    public void UIMenuPadre(Padre padre) {
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
