package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
            long idUsuario = Long.parseLong(id);

            //faz busca e mostra
            PadreServico padreServico = new PadreServico();
            FielServico fielServico = new FielServico();
            List<Padre> padres = new ArrayList<>(padreServico.listarTodos());
            List<Fiel> fiels = new ArrayList<>(fielServico.listarTodos());
            Padre padre = null;
            Fiel fiel = null;

            for (Padre padreTeste : padres){
                if (padreTeste.getId() == idUsuario){
                    padre = padreTeste;
                    JOptionPane.showMessageDialog(null , padre.getNome());
                }
            }

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
