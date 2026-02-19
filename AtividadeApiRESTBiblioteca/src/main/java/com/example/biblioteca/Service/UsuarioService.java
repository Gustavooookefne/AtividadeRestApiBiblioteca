package com.example.biblioteca.Service;

import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.Model.Usuario;
import com.example.biblioteca.Repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Usuario CriarNovoUsuario(Usuario usuario)throws SQLException{
        if(usuario.getEmail().isEmpty()){
            throw new RuntimeException("O Usuario não tem informações");
        }
        return usuario;
    }

    public List<Usuario> ListarTdosOsUsuarios() throws SQLException{
           List<Usuario>usuarios = usuarioRepository.listarUsuario();
           if(usuarios.isEmpty()){
               throw new RuntimeException("A lista esta Vazia");
           }
        return usuarios;
    }

    public Usuario buscarPorId (int id) throws SQLException{
        Usuario usuario = usuarioRepository.buscarPorId(id);
        if(usuario == null){
            throw new RuntimeException("A lista esta Vazia");
        }
        return usuario;
    }

    public Livro AtualizarUsuario(Usuario usuario)throws SQLException{
        Livro livros = usuarioRepository.atualizarUsuario(usuario);
        if(livros == null){
            throw new RuntimeException("Livro não encontrado");
        }

        return usuarioRepository.atualizarUsuario(usuario);
    }

    public void DeletarUsuario(int id) throws SQLException{
        Usuario usuario = usuarioRepository.buscarPorId(id);
        if(usuario == null){
            throw new RuntimeException("A lista esta Vazia");
        }

        usuarioRepository.delete(id);
    }

    public Usuario AtualizarUsuario(Usuario usuario, int id) {
        return usuario;
    }
}
