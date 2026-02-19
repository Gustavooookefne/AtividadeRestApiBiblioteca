package com.example.biblioteca.Repository;

import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.Model.Usuario;
import com.example.biblioteca.Utils.Conexao;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public Usuario novo(Usuario usuario)throws SQLException{
        String sql = """
                INSERT INTO Usuario
                (nome , email)
                VALUES
                (?,?)
                """;

        try (Connection conn = Conexao.connection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
             ps.setInt(1,usuario.getId());
             ps.setString(2,usuario.getNome());
             ps.setString(3,usuario.getEmail());
             ps.executeUpdate();

             try (ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                }
             }
        }
        return usuario;
    }

    public List<Usuario>listarUsuario()throws SQLException{
           List<Usuario>usuarios = new ArrayList<>();
           String sql = """
                   SELECT id
                   nome,
                   email,
                   FROM usuario
                   """;

           try (Connection conn = Conexao.connection();
           PreparedStatement psList = conn.prepareStatement(sql)){
               ResultSet rs = psList.executeQuery();
               while (rs.next()){
                   Usuario usuario = new Usuario();

                   usuario.setId(rs.getInt("id"));
                   usuario.setNome(rs.getString("nome"));
                   usuario.setEmail(rs.getString("email"));

                   usuarios.add(usuario);
               }
           }
        return usuarios;
    }

    public Usuario buscarPorId(int id)throws SQLException{
            Usuario usuario = null;
            String sql = """
                    SELECET id,
                    nome,
                    email,
                    FROM usuario
                    WHERE id = ?         
                    """;

            try (Connection conn = Conexao.connection();
            PreparedStatement psId = conn.prepareStatement(sql)){
                psId.setInt(1,id);
                try (ResultSet rs = psId.executeQuery()){
                    if(rs.next()){
                       usuario = new Usuario();

                       usuario.setNome(rs.getString("nome"));
                       usuario.setEmail(rs.getString("email"));
                       usuario.setId(rs.getInt("id"));

                    }
                }
            }
        return usuario;
    }

    public Usuario atualizarUsuario (Usuario usuario, int idOriginal)throws SQLException{
        String sql = """
                UPDATE usuario
                SET 
                nome = ?,
                email = ?,
                WHERE id = ?
                """;

        try (Connection conn = Conexao.connection();
        PreparedStatement psUPD = conn.prepareStatement(sql)){
            psUPD.setString(1,usuario.getNome());
            psUPD.setString(2,usuario.getEmail());
            psUPD.setInt(3,idOriginal);

            psUPD.executeUpdate();
        }
        return usuario;
    }

    public void delete(int id)throws SQLException{
        String sql = """
                DELETE FROM usuario
                WHERE id = ?
                """;
        try (Connection conn = Conexao.connection();
        PreparedStatement psDEL = conn.prepareStatement(sql)){
            psDEL.setInt(1,id);
            psDEL.executeUpdate();
        }
    }

    public Livro atualizarUsuario(Usuario usuario) {
        return null;
    }
}
