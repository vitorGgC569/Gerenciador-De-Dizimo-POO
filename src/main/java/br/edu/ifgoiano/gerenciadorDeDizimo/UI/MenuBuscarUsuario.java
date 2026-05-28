package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import javax.swing.*;

public class MenuBuscarUsuario {

    /**
     * Inicia a Interface do Menu Busca Usuário
     */
    public void UIMenuBuscarUsuario() {

        while (true) {
            String id = JOptionPane.showInputDialog(null, "Insira o ID do usuário:");
            if (!isInteger(id)) {
                JOptionPane.showMessageDialog(null, "ERRO: É necessário inserir um valor numérico!");
                continue;
            }
            int idUsuario = Integer.parseInt(id);

            //faz busca e mostra

            break; //Finaliza e volta pro menu inicial
        }
    }

    public void UIMenuBuscarTodosUsuarios() {
        //listar todos os usuários
    }

    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
