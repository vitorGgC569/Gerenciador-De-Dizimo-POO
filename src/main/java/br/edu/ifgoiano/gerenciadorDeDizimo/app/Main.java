import br.edu.ifgoiano.gerenciadorDeDizimo.UI.MenuInicial;
import br.edu.ifgoiano.gerenciadorDeDizimo.config.DataInitializer;

void main() {

    DataInitializer.inicializar();

    MenuInicial menuInicial = new MenuInicial();

    while (true) {
        menuInicial.UiMenuInicial();
        break;
    }
}
