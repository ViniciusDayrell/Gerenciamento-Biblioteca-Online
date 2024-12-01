package interfaces;

import javax.swing.*;

import entities.Biblioteca;
import entities.Bibliotecario;
import entities.Livro;
import entities.LivroDigital;
import entities.LivroFisico;
import entities.Usuario;

import java.awt.*;
import java.io.IOException;

public class BibliotecaAppUI {
    private JFrame frame;
    private Biblioteca biblioteca;
    private Bibliotecario bibliotecario;

    public BibliotecaAppUI(Biblioteca biblioteca, Bibliotecario bibliotecario) {
        this.biblioteca = biblioteca;
        this.bibliotecario = bibliotecario;

        // Cria janela
        frame = new JFrame("Biblioteca Online");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Botoes do menu
        JButton btnCadastro = new JButton("Cadastro");
        JButton btnEmprestarLivro = new JButton("Emprestar Livro");
        JButton btnDevolverLivro = new JButton("Devolver Livro");
        JButton btnListagem = new JButton("Listagens");
        JButton btnBuscarLivro = new JButton("Buscar Livro");

        // Adiciona acoes no botao
        btnCadastro.addActionListener(e -> mostrarOpcoesCadastro());
        btnEmprestarLivro.addActionListener(e -> abrirTelaEmprestarLivro());
        btnDevolverLivro.addActionListener(e -> abrirTelaDevolverLivro());
        btnListagem.addActionListener(e -> mostrarOpcoesListagens());
        btnBuscarLivro.addActionListener(e -> abrirBuscaLivro());

        // Adiciona botoes ao painel
        panel.add(btnCadastro);
        panel.add(btnEmprestarLivro);
        panel.add(btnDevolverLivro);
        panel.add(btnListagem);
        panel.add(btnBuscarLivro);

        // Adiciona painel a janela
        frame.add(panel, BorderLayout.CENTER);

        // Deixa a janela visivel
        frame.setVisible(true);
    }

    // Cadastros
    private void mostrarOpcoesCadastro() {
        JPanel opcoesCadastroPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton btnCadastroLivros = new JButton("Cadastro de Livros");
        btnCadastroLivros.addActionListener(e -> abrirCadastroLivro());

        JButton btnCadastroUsuarios = new JButton("Cadastro de Usuarios");
        btnCadastroUsuarios.addActionListener(e -> abrirJanelaCadastroUsuario());

        opcoesCadastroPanel.add(btnCadastroLivros);
        opcoesCadastroPanel.add(btnCadastroUsuarios);

        JOptionPane.showMessageDialog(frame, opcoesCadastroPanel, "Opcoes de Cadastro", JOptionPane.PLAIN_MESSAGE);
    }

    // Cadastro de Usuarios
    private void abrirJanelaCadastroUsuario() {
        JFrame cadastroFrame = new JFrame("Cadastro de Usuario");
        cadastroFrame.setSize(400, 300);
        cadastroFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String senha = senhaField.getText();

                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Todos os campos são obrigatórios.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (biblioteca.buscaUsuarioCpf(cpf) != null) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Usuário já cadastrado.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario usuario = new Usuario(nome, cpf, senha);
                biblioteca.cadastrarUsuario(usuario);
                biblioteca.salvarUsuarios();
                JOptionPane.showMessageDialog(cadastroFrame, "Usuário cadastrado e salvo com sucesso!");

                nomeField.setText("");
                cpfField.setText("");
                senhaField.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(cadastroFrame, "Erro ao salvar os dados: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cadastroFrame.add(nomeLabel);
        cadastroFrame.add(nomeField);

        cadastroFrame.add(cpfLabel);
        cadastroFrame.add(cpfField);

        cadastroFrame.add(senhaLabel);
        cadastroFrame.add(senhaField);

        cadastroFrame.add(new JLabel());
        cadastroFrame.add(salvarButton);

        cadastroFrame.setVisible(true);
    }

    // Cadastro de Livros
    private void abrirCadastroLivro() {
        JPanel opcoesCadastroLivroPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton btnCadastroLivrosDigitais = new JButton("Cadastro de Livros Digitais");
        btnCadastroLivrosDigitais.addActionListener(e -> abrirTelaCadastroLivroDigital());

        JButton btnCadastroLivrosFisicos = new JButton("Cadastro de Livros Fisicos");
        btnCadastroLivrosFisicos.addActionListener(e -> abrirTelaCadastroLivroFisico());

        opcoesCadastroLivroPanel.add(btnCadastroLivrosDigitais);
        opcoesCadastroLivroPanel.add(btnCadastroLivrosFisicos);

        JOptionPane.showMessageDialog(frame, opcoesCadastroLivroPanel, "Opcoes de Cadastro", JOptionPane.PLAIN_MESSAGE);
    }

    private void abrirTelaCadastroLivroDigital() {
        JFrame cadastroFrame = new JFrame("Cadastro de Livro Digital");
        cadastroFrame.setSize(400, 300);
        cadastroFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel tituloLabel = new JLabel("Titulo:");
        JTextField tituloField = new JTextField();

        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField();

        JLabel anoLabel = new JLabel("Ano de Publicacao:");
        JTextField anoField = new JTextField();

        JLabel formatoLabel = new JLabel("Formato:");
        JTextField formatoField = new JTextField();

        JLabel tamanhoLabel = new JLabel("Tamanho:");
        JTextField tamanhoField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            try {
                String titulo = tituloField.getText().trim();
                String autor = autorField.getText().trim();
                String anoPublicacaoStr = anoField.getText().trim();
                String formato = formatoField.getText().trim();
                String tamanhoStr = tamanhoField.getText().trim();

                if (titulo.isEmpty() || autor.isEmpty() || anoPublicacaoStr.isEmpty() || formato.isEmpty()
                        || tamanhoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Todos os campos são obrigatórios.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int anoPublicacao = Integer.parseInt(anoPublicacaoStr);
                int tamanho = Integer.parseInt(tamanhoStr);

                if (biblioteca.buscaLivroPorTitulo(titulo) != null) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Livro já cadastrado.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LivroDigital livroDigital = new LivroDigital(titulo, autor, anoPublicacao, formato, tamanho);
                bibliotecario.cadastraLivros(livroDigital);
                biblioteca.salvarLivros();
                JOptionPane.showMessageDialog(cadastroFrame, "Livro cadastrado e salvo com sucesso!");

                tituloField.setText("");
                autorField.setText("");
                anoField.setText("");
                formatoField.setText("");
                tamanhoField.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(cadastroFrame, "Erro ao salvar os dados: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cadastroFrame.add(tituloLabel);
        cadastroFrame.add(tituloField);

        cadastroFrame.add(autorLabel);
        cadastroFrame.add(autorField);

        cadastroFrame.add(anoLabel);
        cadastroFrame.add(anoField);

        cadastroFrame.add(formatoLabel);
        cadastroFrame.add(formatoField);

        cadastroFrame.add(tamanhoLabel);
        cadastroFrame.add(tamanhoField);

        cadastroFrame.add(new JLabel());
        cadastroFrame.add(salvarButton);

        cadastroFrame.setVisible(true);
    }

    private void abrirTelaCadastroLivroFisico() {
        JFrame cadastroFrame = new JFrame("Cadastro de Livro Fisico");
        cadastroFrame.setSize(400, 300);
        cadastroFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel tituloLabel = new JLabel("Titulo:");
        JTextField tituloField = new JTextField();

        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField();

        JLabel anoLabel = new JLabel("Ano de Publicacao:");
        JTextField anoField = new JTextField();

        JLabel estanteLabel = new JLabel("Estante:");
        JTextField estanteField = new JTextField();

        JLabel prateleiraLabel = new JLabel("Prateleira:");
        JTextField prateleiraField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            try {
                String titulo = tituloField.getText().trim();
                String autor = autorField.getText().trim();
                String anoPublicacaoStr = anoField.getText().trim();
                String estanteStr = estanteField.getText().trim();
                String prateleiraStr = prateleiraField.getText().trim();

                if (titulo.isEmpty() || autor.isEmpty() || anoPublicacaoStr.isEmpty() || estanteStr.isEmpty()
                        || prateleiraStr.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Todos os campos são obrigatórios.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int anoPublicacao = Integer.parseInt(anoPublicacaoStr);
                int estante = Integer.parseInt(estanteStr);
                int pratleira = Integer.parseInt(prateleiraStr);

                if (biblioteca.buscaLivroPorTitulo(titulo) != null) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Livro já cadastrado.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LivroFisico livroFisico = new LivroFisico(titulo, autor, anoPublicacao, estante, pratleira);
                bibliotecario.cadastraLivros(livroFisico);
                biblioteca.salvarLivros();
                JOptionPane.showMessageDialog(cadastroFrame, "Livro cadastrado e salvo com sucesso!");

                tituloField.setText("");
                autorField.setText("");
                anoField.setText("");
                estanteField.setText("");
                prateleiraField.setText("");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(cadastroFrame, "Erro ao salvar os dados: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cadastroFrame.add(tituloLabel);
        cadastroFrame.add(tituloField);

        cadastroFrame.add(autorLabel);
        cadastroFrame.add(autorField);

        cadastroFrame.add(anoLabel);
        cadastroFrame.add(anoField);

        cadastroFrame.add(estanteLabel);
        cadastroFrame.add(estanteField);

        cadastroFrame.add(prateleiraLabel);
        cadastroFrame.add(prateleiraField);

        cadastroFrame.add(new JLabel());
        cadastroFrame.add(salvarButton);

        cadastroFrame.setVisible(true);
    }

    // Emprestimo
    private void abrirTelaEmprestarLivro() {
        JTextField tituloField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataEmprestimoField = new JTextField();

        Object[] mensagem = {
                "Titulo:", tituloField,
                "CPF do Usario:", cpfField,
                "Data do emprestimo:", dataEmprestimoField
        };

        int opcao = JOptionPane.showConfirmDialog(frame, mensagem, "Emprestimo",
                JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText().trim();
            String cpf = cpfField.getText().trim();
            String data = dataEmprestimoField.getText().trim();

            if (titulo.isEmpty() || cpf.isEmpty() || data.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Dados invalidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Livro livro = biblioteca.buscaLivroPorTitulo(titulo);
                Usuario usuario = biblioteca.buscaUsuarioCpf(cpf);

                if (livro == null) {
                    JOptionPane.showMessageDialog(frame, "Nenhum livro encontrado com o titulo: " + titulo,
                            "Livro nao encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else if (usuario == null) {
                    JOptionPane.showMessageDialog(frame, "Nenhum usuario encontrado.",
                            "Usuario nao encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String msg = bibliotecario.gerenciarEmprestimos(livro, usuario, data, bibliotecario);

                    JOptionPane.showMessageDialog(frame, msg);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Dados invalidos. Emprestimo cancelado.");
            }
        }

    }

    // Devolucao
    public void abrirTelaDevolverLivro() {
        JTextField tituloField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataDevolucaoField = new JTextField();

        Object[] mensagem = {
                "Titulo:", tituloField,
                "CPF do Usario:", cpfField,
                "Data da devolucao:", dataDevolucaoField
        };

        int opcao = JOptionPane.showConfirmDialog(frame, mensagem, "Devolucao",
                JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText().trim();
            String cpf = cpfField.getText().trim();
            String data = dataDevolucaoField.getText().trim();

            if (titulo.isEmpty() || cpf.isEmpty() || data.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Dados invalidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String msg = bibliotecario.gerenciarDevolucao(titulo, cpf, data, bibliotecario);

                JOptionPane.showMessageDialog(frame, msg);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Dados invalidos. Devolucao cancelada.");
            }
        }

    }

    // Listagens
    private void mostrarOpcoesListagens() {
        JPanel opcoesListagemPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnListarLivros = new JButton("Listar livros cadastrados");
        btnListarLivros.addActionListener(e -> abrirListagemLivros());

        JButton btnListarLivrosDisponiveis = new JButton("Listar livros disponiveis");
        btnListarLivrosDisponiveis.addActionListener(e -> abrirListagemLivrosDisponiveis());

        JButton btnListarLivrosEmprestados = new JButton("Listar livros emprestados");
        btnListarLivrosEmprestados.addActionListener(e -> abrirListagemLivrosEmprestados());

        JButton btnListarUsuarios = new JButton("Listar usuarios cadastrados");
        btnListarUsuarios.addActionListener(e -> abrirListagemUsuarios());

        opcoesListagemPanel.add(btnListarLivros);
        opcoesListagemPanel.add(btnListarLivrosDisponiveis);
        opcoesListagemPanel.add(btnListarLivrosEmprestados);
        opcoesListagemPanel.add(btnListarUsuarios);

        JOptionPane.showMessageDialog(frame, opcoesListagemPanel, "Opcoes de Listagem", JOptionPane.PLAIN_MESSAGE);
    }

    private void abrirListagemLivros() {
        String detalhesLivros = biblioteca.listarLivros();
        JOptionPane.showMessageDialog(frame, detalhesLivros, "Listagem de Livros", JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirListagemLivrosDisponiveis() {
        String detalhesLivrosDisponiveis = biblioteca.listarLivrosDisponiveis();
        JOptionPane.showMessageDialog(frame, detalhesLivrosDisponiveis, "Listagem de Livros Disponiveis",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirListagemLivrosEmprestados() {
        String detalhesLivrosEmprestados = bibliotecario.mostrarLivrosEmprestados();
        JOptionPane.showMessageDialog(frame, detalhesLivrosEmprestados, "Listagem de Livros Emprestados",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirListagemUsuarios() {
        String detalhesUsuarios = biblioteca.listarUsuarios();
        JOptionPane.showMessageDialog(frame, detalhesUsuarios, "Listagem de Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }

    // Buscar Livros
    private void abrirBuscaLivro() {
        JTextField tituloField = new JTextField();

        Object[] mensagem = {
                "Titulo:", tituloField
        };

        int opcao = JOptionPane.showConfirmDialog(frame, mensagem, "Buscar livro",
                JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText().trim();

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um titulo.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ;
                Livro livro = biblioteca.buscaLivroPorTitulo(titulo);

                if (livro == null) {
                    JOptionPane.showMessageDialog(frame, "Nenhum livro encontrado com o titulo: " + titulo,
                            "Livro nao encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Detalhes do livro:\n\n" + livro.exibirDetalhes(),
                            "Livro Encontrado", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        frame, "Ocorreu um erro ao buscar o livro. Tente novamente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
