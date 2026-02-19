package com.example.biblioteca.Repository;

import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.Utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public Livro novo (Livro livro)throws SQLException{
        String sql = """
                INSERT INTO Livros
                (titulo , autor , anoPublicacao)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.connection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,livro.getId());
            ps.setString(2,livro.getTitulo());
            ps.setString(3,livro.getAutor());
            ps.setInt(4,livro.getAnoPublicacao());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    livro.setId(rs.getInt(1));
                }
            }
        }
        return livro;
    }

    public List<Livro>ListarLivros()throws SQLException{
            List<Livro>livros = new ArrayList<>();

            String sql = """
                    SELECT id,
                    titulo,
                    autor,
                    anoPublicacao,
                    FROM livro
                    """;
            try (Connection conn = Conexao.connection();
                PreparedStatement psList = conn.prepareStatement(sql)){
                ResultSet rs = psList.executeQuery();
                while (rs.next()){
                    Livro livro = new Livro();
                    livro.setId(rs.getInt("id"));
                    livro.setAutor(rs.getString("titulo"));
                    livro.setTitulo(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getInt("anoPublicacao"));
                    livros.add(livro);
                }
            }

        return livros;
    }

    public Livro BuscarPorId(int id) throws SQLException{
        Livro livro = null;
        String sql = """
                SELECT id,
                titulo,
                autor,
                anoPublicacao,
                FROM livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.connection();
        PreparedStatement psID = conn.prepareStatement(sql)){
            psID.setInt(1,id);
            try (ResultSet rs = psID.executeQuery()){
                if(rs.next()){
                    livro = new Livro();
                    livro.setId(rs.getInt("id"));
                    livro.setAutor(rs.getString("titulo"));
                    livro.setTitulo(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getInt("anoPublicaco"));
                }
            }
        }

        return livro;
    }

    public Livro atualizarLivro(Livro livro, int idOriginal)throws SQLException{
        String sql = """
                UPDATE livro
                SET = ?
                titulo = ?
                autor = ?
                anoPublicaco = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.connection();
        PreparedStatement psUPD = conn.prepareStatement(sql)){
            psUPD.setString(1,livro.getTitulo());
            psUPD.setString(2,livro.getAutor());
            psUPD.setInt(3,livro.getAnoPublicacao());
            psUPD.setInt(4,idOriginal);
            psUPD.executeUpdate();

        }
        return livro;
    }

    public void delete(int id)throws SQLException{
        String sql = """
                DELETE FROM livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.connection();
        PreparedStatement psDEL = conn.prepareStatement(sql)){
            psDEL.setInt(1,id);
            psDEL.executeUpdate();
        }
    }

    public Livro atualizarLivro(Livro livro) {
        return livro;
    }
}
