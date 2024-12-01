package entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public Biblioteca(List<Livro> livros, List<Usuario> usuarios) {
        this.livros = livros;
        this.usuarios = usuarios;
    }

    public List<Livro> getLivros() {
        return this.livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    protected String registrarLivro(Livro livro) {
        livros.add(livro);
        return "Livro " + livro.getTitulo() + " registrado com sucesso!";

    }

    public String cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return "Usuario " + usuario.getNome() + " cadastrado com sucesso!";
    }

    public Livro buscaLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                return livro;
            }
        }
        return null;
    }

    public Usuario buscaUsuarioCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean listaVazia() {
        return livros.isEmpty();
    }

    public String listarLivros() {
        StringBuilder detalhes = new StringBuilder();
        boolean existemLivrosDigitais = false;
        boolean existemLivrosFisicos = false;

        if (listaVazia()) {
            return "Nao existem livros no catalago.";
        }

        for (Livro livro : livros) {
            if (livro instanceof LivroDigital) {
                existemLivrosDigitais = true;
                break;
            }
        }

        for (Livro livro : livros) {
            if (livro instanceof LivroFisico) {
                existemLivrosFisicos = true;
                break;
            }
        }

        if (existemLivrosDigitais) {
            detalhes.append("Livros Digitais:\n");
            for (Livro livro : livros) {
                if (livro instanceof LivroDigital) {
                    detalhes.append(livro.exibirDetalhes()).append("\n\n");
                }
            }
        }

        if (existemLivrosFisicos) {
            detalhes.append("Livros Fisicos:\n");
            for (Livro livro : livros) {
                if (livro instanceof LivroFisico) {
                    detalhes.append(livro.exibirDetalhes()).append("\n\n");
                }
            }
        }
        return detalhes.toString();
    }

    public String listarLivrosDisponiveis() {
        StringBuilder detalhes = new StringBuilder();
        boolean algumDisponivel = false;

        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                detalhes.append(livro.exibirDetalhes()).append("\n\n");
                algumDisponivel = true;
            }
        }
        if (!algumDisponivel) {
            return "Nenhum livro esta disponivel no momento.";
        }

        return detalhes.toString();
    }

    public String listarUsuarios() {
        StringBuilder detalhes = new StringBuilder();
        boolean existemUsuariosCadastrados = false;

        for (Usuario usuario : usuarios) {
            if (!(usuario instanceof Bibliotecario)) {
                existemUsuariosCadastrados = true;
                break;
            }
        }

        if (!existemUsuariosCadastrados) {
            return "Nenhum usuario cadastrado.";
        }

        for (Usuario usuario : usuarios) {
            if (!(usuario instanceof Bibliotecario)) {
                detalhes.append(usuario.toString()).append("\n\n");
            }
        }

        return detalhes.toString();
    }

    public void salvarUsuarios() throws IOException {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.bin"))) {
            oos.writeObject(usuarios);
        }
    }

    public void carregarUsuarios() throws IOException, ClassNotFoundException {
        File arquivo = new File("usuarios.bin");

        if (!arquivo.exists()) {
            usuarios = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                usuarios = (List<Usuario>) obj;
            } else {
                usuarios = new ArrayList<>();
            }
        }
    }

    public void salvarLivros() throws IOException {
        if (livros == null) {
            livros = new ArrayList<>();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("livros.bin"))) {
            oos.writeObject(livros);
        }
    }

    public void carregarLivros() throws IOException, ClassNotFoundException {
        File arquivo = new File("livros.bin");

        if (!arquivo.exists()) {
            livros = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                livros = (List<Livro>) obj;
            } else {
                livros = new ArrayList<>();
            }
        }
    }

}
