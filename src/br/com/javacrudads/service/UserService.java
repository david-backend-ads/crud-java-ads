package br.com.javacrudads.service;

import br.com.javacrudads.model.User;
import br.com.javacrudads.repository.UserRepository;

import java.util.List;

public class UserService {


    public String deletarPorId(long id) {
        return repository.deletarPorId(id); // retorna a mensagem direto
    }


    // Instância que qualquer métado do Service pode usar
    private final UserRepository repository = new UserRepository();
    private long proximoId = 1;

    // Public = qualque classe pode chamar
    // void = não retorna nada
    // User user = recebe o usuário que quero salvar
    public void salvar(User user) {

        // Validar nome
        if (user.getNome() == null || user.getNome().isEmpty() || !user.getNome().matches("[A-Za-z ]+")) {
            System.out.println("Nome inválido. Digite apenas letras e espaços.");
            return;
        }

        // Validar CPF
        String cpf = user.getCpf();
        if (cpf == null || !cpf.matches("\\d{11}")) {
            System.out.println("CPF inválido. Digite 11 números sem pontos ou traço.");
            return;
        }

        // Verificar se o CPF já existe para evitar duplicatas
        for (User existente : repository.listarTodos()) {
            if (existente.getCpf().equals(user.getCpf())) {
                System.out.println("CPF já cadastrado! Insira outro.");
                return; // interrompe o salvamento
            }
        }


        // Atribui ID automático ao usuário
        user.setId(proximoId);

        // Incrementa o contador para o próximo usuário
        proximoId++;

        // Salva o usuário no repository após validações
        repository.salvar(user);


    }

    //Métado para retornar a lista de todos os usuários
    public List<User> listarTodos() {
        return repository.listarTodos();
    }

    // Métado que irá fazer a busca por id
    public User buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    // Métado que irá fazer a busca pelo CPF
    public User buscarPorCpf(String cpf) {
        return repository.buscarPorCpf(cpf);
    }

    // Métado que irá fazer a busca pelo nome
    public List<User> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

}
