package entities;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public Biblioteca(List<Livro> livros, List<Usuario> usuarios) {
        this.livros = livros;
        this.usuarios = usuarios;
    }

    protected void registrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro registrado com sucesso!");
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario cadastrado com sucesso!");
    }

    public Livro buscaLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

}
