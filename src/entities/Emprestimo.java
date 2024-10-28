package entities;

import interfaces.IDevolucao;
import interfaces.IEmprestimo;

public class Emprestimo implements IEmprestimo, IDevolucao {
    private Livro livro;
    private Usuario usuario;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, String dataEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public String tituloLivro() {
        return livro.getTitulo();
    }

    @Override
    public void realizarEmprestimo() {
        livro.setDisponivel(false);
        System.out.println("Emprestimo realizado com sucesso!");
    }

    @Override
    public void devolverLivro() {
        livro.setDisponivel(true);
        System.out.println("Livro devolvido com sucesso!");
    }

    public void exibirDetalhesEmprestimo() {
        System.out.println("Livro "
                + tituloLivro()
                + ", emprestado a "
                + getUsuario()
                + ", no dia "
                + getDataEmprestimo());
    }

}
