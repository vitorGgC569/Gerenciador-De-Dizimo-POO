package br.edu.ifgoiano.genreciadorDeDizimo.UI;

import javax.swing.*;

public class MenuInicial {

    MenuLogin menuLogin = new MenuLogin();
    MenuCadastrarUsuario menuCadastrarUsuario = new MenuCadastrarUsuario();
    MenuBuscarUsuario menuBuscarUsuario = new MenuBuscarUsuario();
    MenuAtualizarUsuario menuAtualizarUsuario = new MenuAtualizarUsuario();
    MenuDeletarUsuario menuDeletarUsuario = new MenuDeletarUsuario();

    public void UiMenuInicial(){
        String op = JOptionPane.showInputDialog("""
                ===================================================
                MENU INICIAL
                ===================================================
                [1]: Fazer Login
                [2]: Cadastrar Usuário
                [3]: Buscar Usuário
                [4]: Atualizar Usuário
                [5]: Deletar Usuário
                [6]: Sair
                ===================================================
                Escolha uma Opção:
                """);
        switch (op) {
            case "1":
                menuLogin.UIMenuLogin();
                break;
            case "2":
                menuCadastrarUsuario.UIMenuCadastrarUsuario();
                break;
            case "3":
                menuBuscarUsuario.UIMenuBuscarUsuario();
                break;
            case "4":
                menuAtualizarUsuario.UIMenuAtualizarUsuario();
                break;
            case "5":
                menuDeletarUsuario.UIMenuDeletarUsuario();
                break;
            case "6":
                JOptionPane.showMessageDialog(null, "Saindo...");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção Inválida!");
        }
    }
}
