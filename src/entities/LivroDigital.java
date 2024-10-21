package entities;

public class LivroDigital extends Livro {
    private String formato;
    private int tamanho;

    public LivroDigital(String titulo, String autor, int anoPublicacao, boolean disponibilidade, String formato,
            int tamanho) {
        super(titulo, autor, anoPublicacao, disponibilidade);
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
        return " ";
    }

}
