package entities;

import java.util.List;
import java.util.ArrayList;

public class Bibliotecario extends Usuario {
    private static final long serialVersionUID = 1L;

    private transient List<Emprestimo> emprestimosConcluidos;
    private transient Biblioteca biblioteca;

    public Bibliotecario(String nome, String cpf, String senha, Biblioteca biblioteca) {
        super(nome, cpf, senha);
        this.emprestimosConcluidos = new ArrayList<>();
        this.biblioteca = biblioteca;
    }

    public String cadastraLivros(Livro livro) {
        String mensagem = biblioteca.registrarLivro(livro);
        return mensagem;
    }

    public String mostrarLivrosEmprestados() {
        StringBuilder detalhes = new StringBuilder();

        if (emprestimosConcluidos.isEmpty()) {
            return "Nenhum livro está emprestado no momento.";
        } else {
            detalhes.append("Livros Emprestados:\n");
            for (Emprestimo emprestimo : emprestimosConcluidos) {
                detalhes.append(emprestimo.exibirDetalhesEmprestimo()).append("\n\n");
            }
        }
        return detalhes.toString();
    }

    public String gerenciarEmprestimos(Livro livro, Usuario usuario, String dataEmprestimo,
            Bibliotecario bibliotecario) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo);
            emprestimo.realizarEmprestimo();
            emprestimosConcluidos.add(emprestimo);
            return "Livro " + livro.getTitulo() + " foi emprestado a " + usuario.getNome()
                    + ". Gerenciado por " + bibliotecario.getNome();
        } else {
            return "O livro nao esta disponivel!";
        }
    }

    public String gerenciarDevolucao(String titulo, String cpf, String dataDevolucao, Bibliotecario bibliotecario) {
        Livro livro = biblioteca.buscaLivroPorTitulo(titulo);
        Usuario usuario = biblioteca.buscaUsuarioCpf(cpf);
        if (livro == null) {
            return "Livro nao encontrado no catalago.";
        }

        if (usuario == null) {
            return "Usuario nao encontrado.";
        }

        for (Emprestimo emprestimo : emprestimosConcluidos) {
            if (emprestimo.getLivro().equals(livro)) {
                emprestimo.devolverLivro();
                emprestimosConcluidos.remove(emprestimo);
                return "Livro " + livro.getTitulo() + " foi devolvido por " + usuario.getNome()
                        + ". Gerenciado por " + bibliotecario.getNome();
            }
        }
        return "Nenhum empréstimo encontrado!";
    }

}
