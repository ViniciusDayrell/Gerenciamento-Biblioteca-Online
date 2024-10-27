package entities;

import java.util.List;
import java.util.ArrayList;

/*
 * Gerencia os emprestimos e o catalago dos livros
 * Gerenciar catalago: Cadastro de livros, alteração da localização do livro
 * Deve verificar a disponibilidade do livro, se disponivel(true), empresta
 * Se nao (false), nao eh possivel emprestar
 * Atualiza o valor do atributo disponibilidade na classe livro
 */
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

    // Gerencia Devolucao
    public void gerenciarDevolucao(String titulo, String nome, String dataDevolucao) {
        for (Emprestimo emprestimo : emprestimosConcluidos) {
            // Compara atributos específicos de Livro e Usuario
            if (emprestimo.getLivro().getTitulo().equals(titulo) &&
                    emprestimo.getUsuario().getCpf().equals(nome)) {

                // Realiza a devolução
                emprestimo.devolverLivro();
                emprestimosConcluidos.remove(emprestimo);
                System.out.println("Devolução realizada com sucesso!");

                return; // Finaliza o método após remover o empréstimo
            }
        }
        System.out.println("Nenhum empréstimo encontrado!");
    }

}
