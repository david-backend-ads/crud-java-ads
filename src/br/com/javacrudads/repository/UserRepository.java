package br.com.javacrudads.repository;

import br.com.javacrudads.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public String deletarPorId(long id) {
        for (User user : usuarios) {
            if (user.getId() == id) {
                usuarios.remove(user);
                return "Usuário deletado com sucesso!"; // mensagem de sucesso
            }
        }
        return "Usuário não encontrado!"; // mensagem de erro
    }


    // Private List = Encapsulamento e listar usuários
    private final List<User> usuarios = new ArrayList<>();//

    public void salvar(User user) {
        usuarios.add(user); // receber um user e salvar na lista
    }

    public List<User> listarTodos() {
        return usuarios; // Métado para listar e retornar todos os usuários
    }

    //Buscar usuários pelo ID
    public User buscarPorId(long id) {
        for (User user : usuarios) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // Caso não encontre, retornar null
    }

    //Buscar usuários pelo CPF
    public User buscarPorCpf(String cpf) {
        for (User user : usuarios) {
            if (user.getCpf().equals(cpf)) {
                return user;
            }
        }
        return null;
    }

    //Buscar usuários pelo nome completo ou parte
    public List<User> buscarPorNome(String nome) {
        List<User> encontrados = new ArrayList<>();
        for (User user : usuarios) {
            if (user.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(user);
            }
        }
        return encontrados;
    }

}
