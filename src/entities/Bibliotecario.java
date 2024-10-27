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
        biblioteca.listarLivrosEmprestados();
    }

    // Gerencia emprestimos
    public void gerenciarEmprestimos(Livro livro, Usuario usuario, String dataEmprestimo) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo);
            emprestimo.realizarEmprestimo();
            emprestimosConcluidos.add(emprestimo);
        } else {
            System.out.println("O livro nao esta disponivel!");
        }
    }

    public void gerenciarDevolucao2(String titulo, String cpf, String dataDevolucao) {
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
                return;
            }
        }
        System.out.println("Nenhum empréstimo encontrado!");
    }

}
