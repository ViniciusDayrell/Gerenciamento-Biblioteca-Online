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
    private List<Livro> livrosEmprestados = new ArrayList<>();
    private Biblioteca biblioteca;

    public Bibliotecario(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    // Cadastro de livros
    public void cadastraLivros(Livro livro) {
        biblioteca.registrarLivro(livro);
    }

    // Buscar livro
    public void buscaLivro(String tituloLivro) {
        Livro livro = biblioteca.buscaLivroPorTitulo(tituloLivro);
        if (livro == null) {
            System.out.println("O livro nao existe no catalago!");
        } else if (livro.isDisponibilidade() == false) {
            System.out.println("O livro esta emprestado!");
        } else {
            System.out.println("O livro esta no catalago e disponivel para emprestimo!");
        }
    }

}
