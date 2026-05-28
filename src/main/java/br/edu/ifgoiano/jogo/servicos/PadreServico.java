package br.edu.ifgoiano.jogo.servicos;

import br.edu.ifgoiano.jogo.entidades.Padre;

import java.util.ArrayList;
import java.util.List;

public class PadreServico {
    private List<Padre> padres = new ArrayList<>();
    private Long proximoId = 1L;

    public Padre cadastrar(Padre padre){
        if (padre.getNome() == null || padre.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio!!");
        }
        if (padre.getEmail() == null || padre.getEmail().isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio!!");
        }
        if (padre.getSalario() != null && padre.getSalario() < 0){
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }
        if (emailJaCadastrado(padre.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        padre.setId(proximoId++);
        padres.add(padre);
        return padre;
    }

    public Padre buscarPorId(Long id){
        for (Padre padre : padres){
            if (padre.getId().equals(id))
                return padre;
        }
        return null;
    }

    public List<Padre> listarTodos(){
        return new ArrayList<>(padres);
    }

    public Padre atualizar(Padre padre){
        Padre existente = buscarPorId(padre.getId());
        if (existente == null){throw new IllegalArgumentException("Padre não encontrado.");}
        existente.setNome(padre.getNome());
        existente.setEmail(padre.getEmail());
        existente.setSenhaHash(padre.getSenhaHash());
        existente.setData_Ordenacao(padre.getData_Ordenacao());
        return existente;
    }

    public boolean remover(Long id){
        return padres.removeIf(p -> p.getId().equals(id));
    }

    public Double calcularTotalSalarios(){
        double total = 0.0;
        for (Padre padre : padres){
            if (padre.getSalario() != null) total += padre.getSalario();
        }
        return total;
    }

    private boolean emailJaCadastrado(String email){
        for (Padre padre : padres){
            if (padre.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

}