package br.edu.ifgoiano.genreciadorDeDizimo.UI;

import javax.swing.*;

public class MenuCadastrarUsuario {

    public void UIMenuCadastrarUsuario() {
        while (true) {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do usuario:");
            String senha = JOptionPane.showInputDialog(null, "Digite a senha:");
            String email = JOptionPane.showInputDialog(null, "Digite o email:");
            String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:");
            int isPadre = JOptionPane.showConfirmDialog(null, "Você é padre?");
            boolean admin = isPadre == 0; //0 = Yes

            //cria usuário e cadastra no banco de dados

            break; //Finaliza e volta pro menu inicial
        }
    }
}
