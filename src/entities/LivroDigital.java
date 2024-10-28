package entities;

public class LivroDigital extends Livro {
    private String formato;
    private int tamanho;

    public LivroDigital(String titulo, String autor, int anoPublicacao, String formato,
            int tamanho) {
        super(titulo, autor, anoPublicacao);
        this.formato = formato;
        this.tamanho = tamanho;
    }

    public String getFormato() {
        return formato;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String exibirDetalhes() {
        return "Titulo: "
                + getTitulo()
                + ", Autor: "
                + getAutor()
                + ", Ano de publicacao: "
                + getAnoPublicacao()
                + ", Formato: "
                + getFormato()
                + ", Tamanho: "
                + getTamanho();
    }

}
