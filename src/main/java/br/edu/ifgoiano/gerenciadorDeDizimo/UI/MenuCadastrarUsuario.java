package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.sql.Date;

/** Menu de cadastro de novos usuários (Fiel ou Padre). */
public class MenuCadastrarUsuario {

    /**
     * Inicia a interface de cadastro de usuário.
     */
    public void UIMenuCadastrarUsuario() {
        while (true) {
            String nome     = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
            String email    = JOptionPane.showInputDialog(null, "Digite o email:");
            String senha    = JOptionPane.showInputDialog(null, "Digite a senha:");
            String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:");

            int resposta = JOptionPane.showConfirmDialog(null, "Você é padre?");
            if (resposta == JOptionPane.CANCEL_OPTION) break;

            boolean ehPadre = resposta == JOptionPane.YES_OPTION;

            try {
                if (ehPadre) {
                    String dataStr = JOptionPane.showInputDialog(null, "Insira a data de ordenação (YYYY-MM-DD):");
                    Date dataOrdenacao = Date.valueOf(dataStr);

                    Padre padre = new Padre();
                    padre.setNome(nome);
                    padre.setEmail(email);
                    padre.setSenhaHash(senha);
                    padre.setTelefone(telefone);
                    padre.setData_Ordenacao(dataOrdenacao);

                    new PadreServico().cadastrar(padre);
                    JOptionPane.showMessageDialog(null, "Padre cadastrado com sucesso!");

                } else {
                    String dataStr = JOptionPane.showInputDialog(null, "Insira a data de batismo (YYYY-MM-DD):");
                    Date dataBatismo = Date.valueOf(dataStr);

                    Fiel fiel = new Fiel();
                    fiel.setNome(nome);
                    fiel.setEmail(email);
                    fiel.setSenhaHash(senha);
                    fiel.setTelefone(telefone);
                    fiel.setDataBatismo(dataBatismo);

                    new FielServico().cadastrar(fiel);
                    JOptionPane.showMessageDialog(null, "Fiel cadastrado com sucesso!");
                }

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro de validação: " + e.getMessage());
                continue;
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
                continue;
            }

            break;
        }
    }
}
