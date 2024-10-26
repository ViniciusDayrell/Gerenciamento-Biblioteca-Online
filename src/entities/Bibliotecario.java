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
    public void gerenciarDevolucao(Livro livro, Usuario usuario, String dataDevolucao) {
        for (Emprestimo emprestimo : emprestimosConcluidos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                // emprestimo.calcularMulta(livro, dataDevolucao);
                emprestimo.devolverLivro();
                emprestimosConcluidos.remove(emprestimo);

                return;
            }
        }
        System.out.println("Nenhum emprestimo encontrado!");
    }

}
