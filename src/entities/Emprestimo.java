package entities;

import interfaces.IDevolucao;
import interfaces.IEmprestimo;
//import java.time.LocalDate;

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

    /*
     * Metodo para calcular multa para o usuario
     * Livro fisico deve ser devolvido em ate 15 dias
     * Apos esse periodo, multa de R$2,00 por dia
     */
    /*
     * public void calcularMulta(Livro livro, String dataDevolucao) {
     * if (livro instanceof LivroFisico) {
     * LocalDate d01 = LocalDate.parse(dataEmprestimo);
     * LocalDate d02 = LocalDate.parse(dataDevolucao);
     * }
     * }
     */

    @Override
    public void realizarEmprestimo() {
        livro.setDisponivel(false);
        System.out.println("Emprestimo realizado com sucesso!");
    }

    @Override
    public void cancelarEmprestimo() {

    }

    @Override
    public void devolverLivro() {
        livro.setDisponivel(true);
        System.out.println("Livro devolvido com sucesso!");
    }

}
