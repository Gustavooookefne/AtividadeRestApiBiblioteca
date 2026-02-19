package com.example.biblioteca.Controller;

import org.springframework.web.bind.annotation.*;
import com.example.biblioteca.Model.Usuario;
import com.example.biblioteca.Service.UsuarioService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario save (@RequestBody Usuario usuario){
        try{
            return service.CriarNovoUsuario(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Usuario> findAll(){
        try{
            return service.ListarTdosOsUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Usuario findById (@PathVariable int id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        return service.AtualizarUsuario(usuario, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        try{
            service.DeletarUsuario(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuário com ID: "+id+ " || "+e.getMessage());
        }
    }
}
