package entities;

public class LivroFisico extends Livro {
    private static final long serialVersionUID = 1L;

    private int estante;
    private int pratileira;

    public LivroFisico(String titulo, String autor, int anoPublicacao, int estante,
            int pratileira) {
        super(titulo, autor, anoPublicacao);
        this.estante = estante;
        this.pratileira = pratileira;
    }

    public int getEstante() {
        return estante;
    }

    public int getPratileira() {
        return pratileira;
    }

    public void setEstante(int estante) {
        this.estante = estante;
    }

    public void setPratileira(int pratileira) {
        this.pratileira = pratileira;
    }

    @Override
    public String exibirDetalhes() {
        return "Titulo: "
                + getTitulo()
                + ", Autor: "
                + getAutor()
                + ", Ano de publicacao: "
                + getAnoPublicacao()
                + ", Estante: "
                + getEstante()
                + ", Pratileira: "
                + getPratileira();
    }

}
