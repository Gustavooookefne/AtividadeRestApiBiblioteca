package com.example.biblioteca.Controller;

import com.example.biblioteca.Model.Livro;
import org.springframework.web.bind.annotation.*;
import com.example.biblioteca.Service.LivroService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro save (@RequestBody Livro livro){
        try{
            return service.CadastrarLivro(livro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Livro> findAll(){
        try{
            return service.ListarTodosOsLivros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Livro findById (@PathVariable int id) {
        try {
            return service.BuscarLivroPorID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Livro updateLivro(@PathVariable int id, @RequestBody Livro livro){
        return service.AtualizarLivro(livro, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        try{
            service.DeletarLivro(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o livro com ID: "+id+ " || "+e.getMessage());
        }
    }
}
