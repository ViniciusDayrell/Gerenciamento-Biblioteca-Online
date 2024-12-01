package application;

import java.io.IOException;

import entities.Biblioteca;
import entities.Bibliotecario;
import interfaces.BibliotecaAppUI;

public class App {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = new Biblioteca();

        try {
            biblioteca.carregarLivros();
            biblioteca.carregarUsuarios();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        Bibliotecario bibliotecario = new Bibliotecario("Admin", "000.000.000-00", "admin123", biblioteca);
        biblioteca.cadastrarUsuario(bibliotecario);

        javax.swing.SwingUtilities.invokeLater(() -> new BibliotecaAppUI(biblioteca, bibliotecario));

    }
}
