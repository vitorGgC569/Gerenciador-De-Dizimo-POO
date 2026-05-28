package br.edu.ifgoiano.genreciadorDeDizimo.UI;

import javax.swing.*;

public class MenuDeletarUsuario {

    public void UIMenuDeletarUsuario() {

        while (true) {
            String id = JOptionPane.showInputDialog(null, "Insira o ID do usuário:");
            if (!isInteger(id)) {
                JOptionPane.showMessageDialog(null, "ERRO: É necessário inserir um valor numérico!");
                continue;
            }
            int idUsuario = Integer.parseInt(id);

            //faz busca e verifica se existe
            boolean usuarioValido;
            // = //verifica no banco se usuário o usuario

            if (usuarioValido) {
                // deleta usuário
            }
            else {
                JOptionPane.showMessageDialog(null, "ERRO: Este usuário não existe!");
            }

            break; //Finaliza e volta pro menu inicial
        }
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
