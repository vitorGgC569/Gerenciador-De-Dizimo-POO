package br.edu.ifgoiano.gerenciadorDeDizimo.UI;

import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Fiel;
import br.edu.ifgoiano.gerenciadorDeDizimo.entidades.Padre;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.FielServico;
import br.edu.ifgoiano.gerenciadorDeDizimo.servicos.PadreServico;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class MenuLogin {

    MenuFiel menuFiel = new MenuFiel();
    MenuPadre menuPadre = new MenuPadre();


    /**
     * Inicia a Interface do Menu Login
     */
    public void UIMenuLogin() {
        while (true) {
            String login = JOptionPane.showInputDialog(null, """
                    Insira seu login NOME/ID/EMAIL:
                    (0 - Voltar)
                    """);
            if (login.equals("0")) break;
            String senha = JOptionPane.showInputDialog(null, """
                    Insira sua senha:
                    (0 - Voltar)
                    """);
            if (senha.equals("0")) break;

            Map<Boolean, String> usuarioValido = Map.of();
            // = //verifica no banco se usuário existe e login e senha esta certo
            FielServico fielServico = new FielServico();
            ArrayList<Fiel> fieis = new ArrayList<>(fielServico.listarTodos());
            Fiel fielValidacao = null;
            for (Fiel fiel : fieis){
                if (fiel.getNome() == login || fiel.getEmail() == login || fiel.getId() == Long.parseLong(login)){
                    fielValidacao = fiel;
                    if (fielValidacao.getSenhaHash() == senha){
                        usuarioValido.put(true, "Fiel");
                    }
                }
            }

            PadreServico padreServico = new PadreServico();
            ArrayList<Padre> padres = new ArrayList<>(padreServico.listarTodos());
            Padre padreValidacao = null;
            for (Padre padre : padres){
                if (padre.getNome() == login || padre.getEmail() == login || padre.getId() == Long.parseLong(login)){
                    padreValidacao = padre;
                    if (padreValidacao.getSenhaHash() == senha){
                        usuarioValido.put(true, "Padre");
                    }
                }
            }


            if (usuarioValido.containsValue("Padre")) {
                menuPadre.UIMenuPadre(padreValidacao);
            }else if (usuarioValido.containsValue("Fiel")){
                menuFiel.UIMenuFiel(fielValidacao);
            }
            else {
                JOptionPane.showMessageDialog(null, "ERRO: Este usuário não existe ou credenciais de login estão incorretas!");
            }
        }
    }
}
