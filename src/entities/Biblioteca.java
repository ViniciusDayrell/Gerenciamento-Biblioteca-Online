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
        System.out.println("Livro " + livro.getTitulo() + " registrado com sucesso!");
        System.out.println();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario " + usuario.getNome() + " cadastrado com sucesso!");
    }

    public Livro buscaLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                return livro;
            }
        }
        return null;
    }

    public Usuario buscaUsuarioCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean listaVazia() {
        return livros.isEmpty();
    }

    public void listarLivros() {
        boolean existemLivrosDigitais = false;
        boolean existemLivrosFisicos = false;

        if (listaVazia()) {
            System.out.println("Nao existem livros no catalago.");
            return;
        }

        for (Livro livro : livros) {
            if (livro instanceof LivroDigital) {
                existemLivrosDigitais = true;
                break;
            }
        }

        for (Livro livro : livros) {
            if (livro instanceof LivroFisico) {
                existemLivrosFisicos = true;
                break;
            }
        }

        if (existemLivrosDigitais) {
            System.out.println("Livros Digitais:");
            for (Livro livro : livros) {
                if (livro instanceof LivroDigital) {
                    System.out.println(livro.exibirDetalhes());
                    System.out.println();
                }
            }
        }

        if (existemLivrosFisicos) {
            System.out.println("Livros Fisicos:");
            for (Livro livro : livros) {
                if (livro instanceof LivroFisico) {
                    System.out.println(livro.exibirDetalhes());
                    System.out.println();
                }
            }
        }
    }

    protected void listarLivrosDisponiveis() {
        boolean algumDisponivel = false;

        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println(livro.exibirDetalhes());
                System.out.println();
                algumDisponivel = true;
            }
        }
        if (!algumDisponivel) {
            System.out.println("Nenhum livro esta disponivel no momento.");
            System.out.println();
        }
    }

    public void listarUsuarios() {
        boolean existemUsuariosCadastrados = false;
        for (Usuario usuario : usuarios) {
            if (!(usuario instanceof Bibliotecario)) {
                existemUsuariosCadastrados = true;
                break;
            }
        }

        if (existemUsuariosCadastrados) {
            for (Usuario usuario : usuarios) {
                if (!(usuario instanceof Bibliotecario)) {
                    System.out.println(usuario.toString());
                    System.out.println();
                }
            }
        } else {
            System.out.println("Nenhum usuario cadastrado.");
        }
    }
}
