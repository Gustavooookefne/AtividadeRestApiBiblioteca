package com.example.biblioteca.Controller;

import com.example.biblioteca.Model.Emprestimo;
import com.example.biblioteca.Service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo save (@RequestBody Emprestimo emprestimo){
        try{
            return service.cadastrarNovoEmprestimo(emprestimo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Emprestimo> findAll(){
        try{
            return service.listarTodosOsEmprestimos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Emprestimo findById (@PathVariable int id) {
        try {
            return service.listarEmprestimosPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{usuarioId}/usuario")
    public List<Emprestimo> listarPorUsuario(@PathVariable int usuarioId) {
        return service.listarTodosOsEmprestimos(usuarioId);

    }

    @PutMapping("/{id}")
    public Emprestimo updateEmprestimo(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        return service.atualizarEmprestimo(emprestimo, id);
    }

    @PutMapping ("/devolucao/{id}")
    public void devolver(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        try{
            service.deletarEmprestimo(id, emprestimo.getDataDevolucao());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao devolver o empréstimo com ID: "+id+ " || "+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        service.deletarEmprestimo(id);
    }
}
