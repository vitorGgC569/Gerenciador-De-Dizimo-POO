package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.sql.Date;

public class MenuCadastrarUsuario {

    /**
     * Inicia a Interface do Menu Usuário
     */
    public void UIMenuCadastrarUsuario() {
        while (true) {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do usuario:");
            String senha = JOptionPane.showInputDialog(null, "Digite a senha:");
            String email = JOptionPane.showInputDialog(null, "Digite o email:");
            String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:");
            int isPadre = JOptionPane.showConfirmDialog(null, "Você é padre?");
            boolean admin = isPadre == 0; //0 = Yes
            Date data;
            if (admin) {
                data = Date.valueOf(JOptionPane.showInputDialog(null, "Insira a data de ordenação:"));
            }
            else {
                data = Date.valueOf(JOptionPane.showInputDialog(null, "Insira a data de batismo:"));
            }

            if (!admin){
                FielServico fielServico = new FielServico();
                Fiel fiel = new Fiel(data, 0L, nome, email, senha, admin, telefone);
            }else if (admin){
                PadreServico padreServico = new PadreServico();
                Padre padre = new Padre(data, 0L, nome, email, senha, admin, telefone);
            }

            //cria usuário e cadastra no banco de dados

            break; //Finaliza e volta pro menu inicial
        }


    }
}
