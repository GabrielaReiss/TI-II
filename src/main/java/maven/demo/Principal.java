package maven.demo;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        DAO dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        int option;

        if (!dao.conectar()) {
            System.err.println("Falha ao conectar ao banco de dados.");
            return;
        }

        do {
            // Exibir menu
            System.out.println("=== Menu ===");
            System.out.println("1 - Inserir Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Atualizar Livro");
            System.out.println("4 - Excluir Livro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após o número

            switch (option) {
                case 1: // Inserir Livro
                    System.out.print("Digite o ID do livro: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o número
                    System.out.print("Digite o nome do livro: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o número de páginas: ");
                    int paginas = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o número

                    Livro livro = new Livro(id, nome, paginas);
                    if (dao.inserirLivro(livro)) {
                        System.out.println("Inserção com sucesso -> " + livro.toString());
                    } else {
                        System.out.println("Falha na inserção.");
                    }
                    break;

                case 2: // Listar Livros
                    System.out.println("==== Listar Livros === ");
                    Livro[] livros = dao.getLivros();
                    if (livros != null) {
                        for (Livro l : livros) {
                            System.out.println(l.toString());
                        }
                    } else {
                        System.out.println("Nenhum livro encontrado.");
                    }
                    break;

                case 3: // Atualizar Livro
                    System.out.print("Digite o ID do livro a ser atualizado: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o número
                    System.out.print("Digite o novo nome do livro: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o novo número de páginas: ");
                    paginas = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o número

                    livro = new Livro(id, nome, paginas);
                    if (dao.atualizarLivro(livro)) {
                        System.out.println("Atualização com sucesso -> " + livro.toString());
                    } else {
                        System.out.println("Falha na atualização.");
                    }
                    break;

                case 4: // Excluir Livro
                    System.out.print("Digite o ID do livro a ser excluído: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o número
                    if (dao.excluirLivro(id)) {
                        System.out.println("Exclusão com sucesso.");
                    } else {
                        System.out.println("Falha na exclusão.");
                    }
                    break;

                case 5: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (option != 5);

        dao.close();
        scanner.close();
    }
}