package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDeletarUsuario {

    /**
     * Inicia a Interface do Menu Deletar Usuário
     */
    public void UIMenuDeletarUsuario() {

        while (true) {
            String id = JOptionPane.showInputDialog(null, "Insira o ID do usuário:");
            if (!isInteger(id)) {
                JOptionPane.showMessageDialog(null, "ERRO: É necessário inserir um valor numérico!");
                continue;
            }
            int idUsuario = Integer.parseInt(id);

            //faz busca e verifica se existe
            boolean usuarioValido = false;
            // = //verifica no banco se usuário o usuario

            if (usuarioValido) {
                // deleta usuário
            }
            else {
                JOptionPane.showMessageDialog(null, "ERRO: Este usuário não existe!");
            }

            PadreServico padreServico = new PadreServico();
            FielServico fielServico = new FielServico();
            List<Padre> padres = new ArrayList<>(padreServico.listarTodos());
            List<Fiel> fieis = new ArrayList<>(fielServico.listarTodos());
            Padre padre = null;
            Fiel fiel = null;

            for (Padre padreTeste : padres){
                if (padreTeste.getId() == idUsuario){
                    padre = padreTeste;
                    String nomePadre = padre.getNome();

                    padreServico.remover(padre.getId());

                    JOptionPane.showMessageDialog(null , nomePadre + " foi deletado!");
                }
            }

            for (Fiel fielTest : fieis){
                if (fielTest.getId() == idUsuario){
                    fiel = fielTest;
                    String nomeFiel = fiel.getNome();

                    fielServico.remover(fiel.getId());

                    JOptionPane.showMessageDialog(null , nomeFiel + " foi deletado!");
                }
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
