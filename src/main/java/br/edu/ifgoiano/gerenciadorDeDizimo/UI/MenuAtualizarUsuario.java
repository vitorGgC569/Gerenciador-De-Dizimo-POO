package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MenuAtualizarUsuario {

    MenuCadastrarUsuario menuCadastrarUsuario = new MenuCadastrarUsuario();

    /**
     * Inicia a Interface do Menu Atualizar Usuário
     */
    public void UIMenuAtualizarUsuario() {

        String id = JOptionPane.showInputDialog(null, "Insira o ID do usuário:");

        if (!isInteger(id)) {
            JOptionPane.showMessageDialog(null, "ERRO: É necessário inserir um valor numérico!");
            return;
        }

        long idUsuario = Long.parseLong(id);

        String nome     = JOptionPane.showInputDialog(null, "Digite o nome:");
        String email    = JOptionPane.showInputDialog(null, "Digite o email:");
        String senha    = JOptionPane.showInputDialog(null, "Digite a senha:");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:");

        int resposta = JOptionPane.showConfirmDialog(null, "Você é padre?");

        boolean ehPadre = resposta == JOptionPane.YES_OPTION;

        try {

            if (ehPadre) {

                String dataStr = JOptionPane.showInputDialog(
                        null,
                        "Insira a data de ordenação (YYYY-MM-DD):"
                );

                Date dataOrdenacao = Date.valueOf(dataStr);

                Padre padre = new Padre();

                padre.setId(idUsuario); // MUITO IMPORTANTE
                padre.setNome(nome);
                padre.setEmail(email);
                padre.setSenhaHash(senha);
                padre.setTelefone(telefone);
                padre.setData_Ordenacao(dataOrdenacao);

                PadreServico padreServico = new PadreServico();

                padreServico.atualizar(padre);

                JOptionPane.showMessageDialog(
                        null,
                        "Padre atualizado com sucesso!"
                );

            } else {

                String dataStr = JOptionPane.showInputDialog(
                        null,
                        "Insira a data de batismo (YYYY-MM-DD):"
                );

                Date dataBatismo = Date.valueOf(dataStr);

                Fiel fiel = new Fiel();

                fiel.setId(idUsuario); // IMPORTANTÍSSIMO
                fiel.setNome(nome);
                fiel.setEmail(email);
                fiel.setSenhaHash(senha);
                fiel.setTelefone(telefone);
                fiel.setDataBatismo(dataBatismo);

                FielServico fielServico = new FielServico();

                fielServico.atualizar(fiel);

                JOptionPane.showMessageDialog(
                        null,
                        "Fiel atualizado com sucesso!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro: " + e.getMessage()
            );
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
