package br.com.javacrudads;

import br.com.javacrudads.model.User;
import br.com.javacrudads.service.UserService;

import java.util.List;
import java.util.Scanner;

// Menu interativo
public class Main {

    public static void main(String[] args) {
        UserService service = new UserService(); // Serviço que gerencia usuários
        Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário
        int opcao;

        while (true) {
            mostrarMenu(); // Exibe o menu

            // Ler e validar a opção do menu
            opcao = lerOpcao(scanner);

            // Executa a função escolhida
            switch (opcao) {
                case 1 -> adicionarUsuario(service, scanner); // Adicionar novo usuário
                case 2 -> listarUsuarios(service);            // Listar todos os usuários
                case 3 -> buscarPorId(service, scanner);      // Buscar usuário por ID
                case 4 -> buscarPorCpf(service, scanner);     // Buscar usuário por CPF
                case 5 -> buscarPorNome(service, scanner);    // Buscar usuário por nome
                case 6 -> {                                   // Sair do programa
                    System.out.println("Saindo do programa, até mais!");
                    return;
                }
                case 7 -> deletarUsuario(service, scanner);   // Deletar usuário por ID
                default -> System.out.println("Opção inválida!"); // Nunca deve acontecer
            }
        }
    }

    // Método que exibe o menu
    private static void mostrarMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Adicionar usuário");
        System.out.println("2 - Listar todos os usuários");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Buscar por CPF");
        System.out.println("5 - Buscar por Nome");
        System.out.println("6 - Sair");
        System.out.println("7 - Deletar usuário");
    }

    // Método que lê e valida a opção do menu
    private static int lerOpcao(Scanner scanner) {
        int opcao;
        while (true) {
            System.out.print("Escolha uma opção: ");
            if (scanner.hasNextInt()) { // Verifica se é um número
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
                if (opcao >= 1 && opcao <= 7) return opcao; // Verifica intervalo válido
            } else {
                scanner.nextLine(); // Limpa entrada inválida
            }
            System.out.println("Opção inválida! Digite um número de 1 a 7.");
        }
    }

    // Método que exibe os dados de um usuário
    private static void mostrarUsuario(User user) {
        System.out.println("ID: " + user.getId() + ", Nome: " + user.getNome() + ", CPF: " + user.getCpf());
    }

    // Adiciona um usuário, validando nome e CPF
    private static void adicionarUsuario(UserService service, Scanner scanner) {
        String nome = lerNome(scanner); // Valida nome
        String cpf = lerCpf(service, scanner); // Valida CPF e evita duplicatas

        User user = new User();
        user.setNome(nome);
        user.setCpf(cpf);
        service.salvar(user); // Salva usuário no repositório
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // Valida o nome (não vazio, apenas letras e espaços)
    private static String lerNome(Scanner scanner) {
        String nome;
        while (true) {
            System.out.print("Digite seu Nome: ");
            nome = scanner.nextLine().trim();
            if (!nome.isEmpty() && nome.matches("[A-Za-zÀ-ÿ ]+")) return nome;
            System.out.println("Nome inválido! Digite apenas letras e espaços.");
        }
    }

    // Valida o CPF (11 números, não duplicado)
    private static String lerCpf(UserService service, Scanner scanner) {
        String cpf;
        while (true) {
            System.out.print("Digite seu CPF (11 números, sem pontos ou traço): ");
            cpf = scanner.nextLine().trim();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("CPF inválido! Digite 11 números sem pontos ou traço.");
                continue;
            }
            if (service.buscarPorCpf(cpf) != null) {
                System.out.println("CPF já cadastrado! Digite outro.");
                continue;
            }
            return cpf;
        }
    }

    // Lista todos os usuários cadastrados
    private static void listarUsuarios(UserService service) {
        List<User> usuarios = service.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados!");
        } else {
            usuarios.forEach(Main::mostrarUsuario); // Versão enxuta com forEach
        }
    }

    // Busca usuário por ID, validando entrada numérica
    private static void buscarPorId(UserService service, Scanner scanner) {
        long id;
        while (true) {
            System.out.print("Digite o ID do usuário: ");
            if (scanner.hasNextLong()) { // Verifica se é número
                id = scanner.nextLong();
                scanner.nextLine(); // Limpa buffer
                if (id > 0) break; // ID positivo
            } else {
                scanner.nextLine();
            }
            System.out.println("ID inválido! Digite um número positivo.");
        }

        User user = service.buscarPorId(id);
        if (user != null) mostrarUsuario(user);
        else System.out.println("Usuário não encontrado!");
    }

    // Busca usuário por CPF
    private static void buscarPorCpf(UserService service, Scanner scanner) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine().trim();

        User user = service.buscarPorCpf(cpf);
        if (user != null) mostrarUsuario(user);
        else System.out.println("Usuário não encontrado!");
    }

    // Busca usuário por nome ou parte do nome
    private static void buscarPorNome(UserService service, Scanner scanner) {
        System.out.print("Digite o nome ou parte do nome: ");
        String nome = scanner.nextLine().trim();

        List<User> encontrados = service.buscarPorNome(nome);
        if (!encontrados.isEmpty()) encontrados.forEach(Main::mostrarUsuario);
        else System.out.println("Usuário não encontrado!");
    }

    // Deleta usuário pelo ID, mostrando mensagem de sucesso ou erro
    private static void deletarUsuario(UserService service, Scanner scanner) {
        long id;
        while (true) {
            System.out.print("Digite o ID do usuário a deletar: ");
            if (scanner.hasNextLong()) { // Verifica se é número
                id = scanner.nextLong();
                scanner.nextLine(); // Limpa buffer
                if (id > 0) break; // ID positivo
            } else {
                scanner.nextLine(); // Limpa entrada inválida
            }
            System.out.println("ID inválido! Digite um número positivo.");
        }

        String mensagem = service.deletarPorId(id); // Retorna mensagem do Service
        System.out.println(mensagem);
    }
}
