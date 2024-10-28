package entities;

import java.util.List;
import java.util.ArrayList;

public class Bibliotecario extends Usuario {
    private List<Emprestimo> emprestimosConcluidos;
    private Biblioteca biblioteca;

    public Bibliotecario(String nome, String cpf, String senha, Biblioteca biblioteca) {
        super(nome, cpf, senha);
        this.emprestimosConcluidos = new ArrayList<>();
        this.biblioteca = biblioteca;
    }

    // Cadastro de livros
    public void cadastraLivros(Livro livro) {
        biblioteca.registrarLivro(livro);
    }

    // Mostra os status dos livros
    public void mostrarLivrosDisponiveis() {
        biblioteca.listarLivrosDisponiveis();
    }

    public void mostrarLivrosEmprestados() {
        if (emprestimosConcluidos.isEmpty()) {
            System.out.println("Nenhum livro está emprestado no momento.");
        } else {
            for (Emprestimo emprestimo : emprestimosConcluidos) {
                emprestimo.exibirDetalhesEmprestimo();
            }
        }
    }

    // Gerencia emprestimos
    public void gerenciarEmprestimos(Livro livro, Usuario usuario, String dataEmprestimo, Bibliotecario bibliotecario) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo);
            emprestimo.realizarEmprestimo();
            emprestimosConcluidos.add(emprestimo);
            System.out.println("Livro " + livro.getTitulo() + " foi emprestado a " + usuario.getNome()
                    + ". Gerenciado por " + bibliotecario.getNome());
        } else {
            System.out.println("O livro nao esta disponivel!");
        }
    }

    public void gerenciarDevolucao(String titulo, String cpf, String dataDevolucao, Bibliotecario bibliotecario) {
        Livro livro = biblioteca.buscaLivroPorTitulo(titulo);
        Usuario usuario = biblioteca.buscaUsuarioCpf(cpf);
        if (livro == null) {
            System.out.println("Livro nao encontrado no catalago.");
            return;
        }

        if (usuario == null) {
            System.out.println("Usuario nao encontrado.");
            return;
        }

        for (Emprestimo emprestimo : emprestimosConcluidos) {
            if (emprestimo.getLivro().equals(livro)) {
                emprestimo.devolverLivro();
                emprestimosConcluidos.remove(emprestimo);
                System.out.println("Livro " + livro.getTitulo() + " foi devolvido por " + usuario.getNome()
                        + ". Gerenciado por " + bibliotecario.getNome());
                return;
            }
        }
        System.out.println("Nenhum empréstimo encontrado!");
    }

}
