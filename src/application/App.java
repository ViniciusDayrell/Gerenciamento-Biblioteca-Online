package application;

/*
 * Criar tratamento de erros como:
 * Livros iguais nao podem ser cadastrados
 */
import java.util.Scanner;

import entities.Biblioteca;
import entities.Bibliotecario;
import entities.Livro;
import entities.LivroDigital;
import entities.LivroFisico;
import entities.Usuario;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        System.out.println("Bem-vindo a Biblioteca!");
        System.out.println("Um Bibliotecario deve ser cadastrado!");
        System.out.printf("Nome: ");
        String nome = sc.nextLine();
        System.out.printf("CPF: ");
        String cpf = sc.nextLine();
        System.out.printf("Senha: ");
        String senha = sc.nextLine();
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha, biblioteca);
        biblioteca.cadastrarUsuario(bibliotecario);
        System.out.println();

        do {
            System.out.println("-------Biblioteca-------");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Emprestar livro");
            System.out.println("3 - Devolver livro");
            System.out.println("4 - Listagens");
            System.out.println("5 - Buscar livros");
            System.out.println("6 - Sair");
            System.out.printf("Escolha uma opcao: ");
            opcao = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcao) {
                case 1:

                    System.out.println("-------Cadastros-------");
                    System.out.println("1 - Registrar Livros");
                    System.out.println("2 - Cadastrar usuario");
                    System.out.printf("Escolha uma opcao: ");
                    int cadastro = sc.nextInt();
                    System.out.println();

                    switch (cadastro) {
                        case 1:
                            System.out.println("----Registro Livro----");
                            System.out.println("1 - Livro fisico");
                            System.out.println("2 - Livro digital");
                            System.out.printf("Escolha uma opcao: ");
                            int tipo = sc.nextInt();
                            System.out.println();

                            switch (tipo) {
                                case 1:
                                    System.out.println("----Livro Fisico----");
                                    System.out.printf("Titulo: ");
                                    sc.nextLine();
                                    String titulo = sc.nextLine();
                                    System.out.printf("Autor: ");
                                    String autor = sc.nextLine();
                                    System.out.printf("Ano de publicacao: ");
                                    int anoPublicacao = sc.nextInt();
                                    System.out.printf("Estante: ");
                                    int estante = sc.nextInt();
                                    System.out.printf("Pratileira: ");
                                    int pratileira = sc.nextInt();

                                    LivroFisico livroFisico = new LivroFisico(titulo, autor, anoPublicacao, estante,
                                            pratileira);
                                    bibliotecario.cadastraLivros(livroFisico);
                                    break;

                                case 2:
                                    System.out.println("----Livro Digital----");
                                    System.out.printf("Titulo: ");
                                    sc.nextLine();
                                    String tituloDigital = sc.nextLine();
                                    System.out.printf("Autor: ");
                                    String autorDigital = sc.nextLine();
                                    System.out.printf("Ano de publicacao: ");
                                    int anoPublicacaoDigital = sc.nextInt();
                                    sc.nextLine();
                                    System.out.printf("Formato (PDF, ePub): ");
                                    String formato = sc.nextLine();
                                    System.out.printf("Tamanho: ");
                                    int tamanho = sc.nextInt();

                                    LivroDigital livroDigital = new LivroDigital(tituloDigital, autorDigital,
                                            anoPublicacaoDigital, formato, tamanho);
                                    bibliotecario.cadastraLivros(livroDigital);
                                    break;

                                default:
                                    System.out.println("Opcao invalida! Retornando ao menu...");
                                    break;
                            }
                            break;

                        case 2:
                            System.out.println("----Cadastro Usuario----");
                            sc.nextLine();
                            System.out.printf("Nome: ");
                            String nomeUsuario = sc.nextLine();
                            System.out.printf("CPF: ");
                            String cpfUsuario = sc.nextLine();
                            System.out.printf("Senha: ");
                            String senhaUsuario = sc.nextLine();

                            if (biblioteca.buscaUsuarioCpf(cpfUsuario) == null) {
                                Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, senhaUsuario);
                                biblioteca.cadastrarUsuario(usuario);
                            } else {
                                System.out.println("Usuario ja cadastrado. Retornando ao menu...");
                                System.out.println();
                            }
                            break;

                        default:
                            System.out.println("Opcao invalida! Retornando ao menu...");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("----Emprestimo----");
                    System.out.printf("Titulo do livro: ");
                    String buscaTitulo = sc.nextLine();
                    System.out.printf("CPF do usuario: ");
                    String cpfUsuario = sc.nextLine();
                    if (biblioteca.buscaLivroPorTitulo(buscaTitulo) == null) {
                        System.out.println("Livro nao encontrado! Retornando ao menu...");
                        break;
                    } else if (biblioteca.buscaUsuarioCpf(cpfUsuario) == null) {
                        System.out.println("Usuario nao encontrado! Retornando ao menu...");
                        break;
                    } else {
                        System.out.printf("Data do emprestimo: ");
                        String dataEmprestimo = sc.nextLine();
                        bibliotecario.gerenciarEmprestimos(biblioteca.buscaLivroPorTitulo(buscaTitulo),
                                biblioteca.buscaUsuarioCpf(cpfUsuario), dataEmprestimo, bibliotecario);
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println("----Devolucao----");
                    System.out.printf("Titulo do livro: ");
                    String buscaTitulo2 = sc.nextLine();
                    System.out.printf("CPF do usuario: ");
                    String cpfUsuario2 = sc.nextLine();
                    if (biblioteca.buscaLivroPorTitulo(buscaTitulo2) == null) {
                        System.out.println("Livro nao encontrado! Retornando ao menu...");
                        break;
                    } else if (biblioteca.buscaUsuarioCpf(cpfUsuario2) == null) {
                        System.out.println("Usuario nao encontrado! Retornando ao menu...");
                        break;
                    } else {
                        System.out.printf("Data da devolucao: ");
                        String dataDevolucao = sc.nextLine();
                        bibliotecario.gerenciarDevolucao(buscaTitulo2,
                                cpfUsuario2, dataDevolucao, bibliotecario);
                    }
                    break;

                case 4:
                    System.out.println("----Listagens----");
                    System.out.println("1 - Listar livros");
                    System.out.println("2 - Listar usuarios");
                    System.out.printf("Escolha uma opcao: ");
                    int lista = sc.nextInt();
                    System.out.println();

                    switch (lista) {
                        case 1:
                            System.out.println("----Listar livros----");
                            System.out.println("1 - Listar todos os livros");
                            System.out.println("2 - Listar livros disponiveis");
                            System.out.println("3 - Listar livros emprestados");
                            System.out.printf("Escolha uma opcao: ");
                            int listaLivro = sc.nextInt();
                            System.out.println();

                            switch (listaLivro) {
                                case 1:
                                    System.out.println("----Livros----");
                                    biblioteca.listarLivros();
                                    break;

                                case 2:
                                    System.out.println("----Livros disponiveis----");
                                    bibliotecario.mostrarLivrosDisponiveis();
                                    System.out.println();
                                    break;

                                case 3:
                                    System.out.println("----Livros emprestados----");
                                    bibliotecario.mostrarLivrosEmprestados();
                                    System.out.println();
                                    break;

                                default:
                                    System.out.println("Opcao invalida! Retornando ao menu...");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("----Listar Usuarios----");
                            biblioteca.listarUsuarios();
                            break;

                        default:
                            System.out.println("Opcao invalida! Retornando ao menu...");
                            break;
                    }
                    break;

                case 5:
                    System.out.println("----Buscar livro----");
                    System.out.printf("Nome do livro: ");
                    String livroBuscado = sc.nextLine();

                    Livro livro = biblioteca.buscaLivroPorTitulo(livroBuscado);

                    if (livro == null) {
                        System.out.println("Livro nao encontrado!");
                        System.out.println();
                        break;
                    } else {
                        System.out.println("Livro encontrado:");
                        System.out.println(livro.exibirDetalhes());
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Encerrando o sistema... Obrigado por usar a Biblioteca!");
                    break;

                default:
                    System.out.println("Opcao invalida! Retornando ao menu...");
                    break;
            }
        } while (opcao != 6);
        sc.close();

    }
}
