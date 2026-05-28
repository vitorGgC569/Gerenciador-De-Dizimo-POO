package br.edu.ifgoiano.jogo.UI;

import javax.swing.*;

public class MenuLogin {

    MenuFiel menuFiel = new MenuFiel();
    MenuPadre menuPadre = new MenuPadre();

    /**
     * Inicia a Interface do Menu Login
     */
    public void UIMenuLogin() {
        while (true) {
            String login = JOptionPane.showInputDialog(null, """
                    Insira seu login:
                    (0 - Voltar)
                    """);
            if (login.equals("0")) break;
            String senha = JOptionPane.showInputDialog(null, """
                    Insira sua senha:
                    (0 - Voltar)
                    """);
            if (senha.equals("0")) break;

            boolean usuarioValido;
            // = //verifica no banco se usuário existe e login e senha esta certo

            if (usuarioValido) {
                //verifico tipo de usuário
                if () { //se usuário for tipo fiel
                    menuFiel.UIMenuFiel();
                }
                else { //se usuário for tipo padre
                    menuPadre.UIMenuPadre();
                }
                break;
            }
            else {
                JOptionPane.showMessageDialog(null, "ERRO: Este usuário não existe ou credenciais de login estão incorretas!");
            }
        }
    }
}
