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
            List<Fiel> fieis = new ArrayList<>(fielServico.listarTodos());
            Padre padre = null;
            Fiel fiel = null;

            for (Padre padreTeste : padres){
                if (padreTeste.getId() == idUsuario){
                    padre = padreTeste;
                    JOptionPane.showMessageDialog(null , padre.getNome());
                }
            }

            for (Fiel fielTest : fieis){
                if (fielTest.getId() == idUsuario){
                    fiel = fielTest;
                    JOptionPane.showMessageDialog(null , fiel.getNome());
                }
            }

            break; //Finaliza e volta pro menu inicial
        }
    }

    public void UIMenuBuscarTodosUsuarios() {
        FielServico fielServico = new FielServico();
        List<Fiel> fieis = new ArrayList<>(fielServico.listarTodos());
        PadreServico padreServico = new PadreServico();
        List<Padre> padres = new ArrayList<>(padreServico.listarTodos());

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        sb.append("Tabela FIEIS:\n");
        sb2.append("Tabela Padres:\n");

        int count = 1;
        int counter = 1;

        for (Fiel fielTest : fieis) {

            sb.append(fielTest.getNome() + " " + fielTest.getId());

            if (count % 5 == 0) {
                sb.append("\n");
            } else {
                sb.append(" | ");
            }

            count++;
        }

        for (Padre padretest : padres) {

            sb.append(padretest.getNome() + " " +  padretest.getId());

            if (count % 5 == 0) {
                sb.append("\n");
            }else  {
                sb.append(" | ");
            }
            count++;
        }

        JOptionPane.showMessageDialog(null, sb.toString() + "\n" + sb2.toString());
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
