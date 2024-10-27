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

        // Verifica se existem livros de cada tipo
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

        // Faz a listagem dos livros caso existam
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
        }
    }

    protected void listarLivrosEmprestados() {
        boolean algumEmprestado = false;

        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                System.out.println(livro.exibirDetalhes());
                System.out.println();
                algumEmprestado = true;
            }
        }

        if (!algumEmprestado) {
            System.out.println("Nenhum livro está emprestado no momento.");
            System.out.println();
        }
    }
}
