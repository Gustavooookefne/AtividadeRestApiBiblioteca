package com.example.biblioteca.Service;

import com.example.biblioteca.Model.Emprestimo;
import com.example.biblioteca.Repository.EmprestimoRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

    public Emprestimo cadastrarNovoEmprestimo(Emprestimo emprestimo)throws SQLException{
        return emprestimo;
    }

    public List<Emprestimo> listarTodosOsEmprestimos()throws SQLException{
        List<Emprestimo>emprestimos = emprestimoRepository.listarTodosOsEmprestimos();
        if(emprestimos.isEmpty()){
            throw new RuntimeException("A lista esta vazia");
        }
        return emprestimos;
    }

    public Emprestimo listarEmprestimosPorId(int id)throws SQLException{
        Emprestimo emprestimo = emprestimoRepository.listarPorId(id);
        if(emprestimo == null){
            throw new RuntimeException("A lista id esta vazia");
        }
        return emprestimo;
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo)throws SQLException{
        Emprestimo emprestimos = emprestimoRepository.atualizarEmprestimo(emprestimo);
        if(emprestimos == null){
            throw new RuntimeException("Não tem emprestimo para atualizar");
        }
        return emprestimos;
    }

    public void deletarEmprestimo(int id, LocalDate dataDevolucao)throws SQLException{
        Emprestimo emprestimo = emprestimoRepository.listarPorId(id);

        if(emprestimo == null){
            throw new RuntimeException("Emprestimo não existe");
        }
    }

    public List<Emprestimo> listarTodosOsEmprestimos(int usuarioId) {
        return List.of();
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo, int id) {
        return emprestimo;
    }

    public void deletarEmprestimo(int id) {
    }
}
