package com.example.biblioteca.Repository;

import com.example.biblioteca.Model.Emprestimo;
import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.Model.Usuario;
import com.example.biblioteca.Utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public Emprestimo novo(Emprestimo emprestimo)throws SQLException{
        String sql = """
                INSERT INTO Emprestimo
                (livro, usuario, dataEmprestimo, dataDevolucao)
                VALUES
                (?,?,?,?)
                """;
            try (Connection conn = Conexao.connection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                 ps.setInt(1,emprestimo.getId());
                 ps.setInt(2,emprestimo.getLivro().getId());
                 ps.setInt(3,emprestimo.getUsuario().getId());
                 ps.setDate(4, Date.valueOf(emprestimo.getDataEmprstimo()));
                 ps.setDate(5,Date.valueOf(emprestimo.getDataDevolucao()));
                 ps.executeUpdate();

                 try (ResultSet rs = ps.getGeneratedKeys()){
                     if(rs.next()){
                         emprestimo.setId(rs.getInt(1));
                     }
                 }
            }

        return emprestimo;
    }

    public List<Emprestimo> listarTodosOsEmprestimos()throws SQLException{
           List<Emprestimo> emprestimos = new ArrayList<>();

           String sql = """
                   SELECT id,
                   livro,
                   usuario,
                   dataEmprestimo,
                   dataDevolucao,
                   FROM emprestimo
                   """;
            try (Connection conn = Conexao.connection();
                PreparedStatement psList = conn.prepareStatement(sql)){
                ResultSet rs = psList.executeQuery();
                while (rs.next()){
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.setId(rs.getInt("id"));

                    Livro livro = new Livro();
                    livro.setId(rs.getInt("livro"));
                    emprestimo.setLivro(livro);

                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario"));
                    emprestimo.setUsuario(usuario);

                    emprestimo.setDataEmprstimo(rs.getDate("dataEmprestimo").toLocalDate());

                    Date dataDev = rs.getDate("dataDevolucao");
                    if (dataDev != null){
                        emprestimo.setDataDevolucao(dataDev.toLocalDate());
                    }
                    emprestimos.add(emprestimo);
                }
            }


        return emprestimos;
    }

    public Emprestimo listarPorId(int id)throws SQLException{
           Emprestimo emprestimo = null;

           String sql = """
                   SELECT id,
                   livro,
                   usuario,
                   dataEmprestimo,
                   dataDevolucao,
                   FROM emprestimo
                   WHERE id = ?
                   """;

           try (Connection conn =Conexao.connection();
           PreparedStatement psList = conn.prepareStatement(sql)){
               psList.setInt(1,id);
               ResultSet rs = psList.executeQuery();
               while (rs.next()){
                   emprestimo = new Emprestimo();
                   emprestimo.setId(rs.getInt("id"));

                   Livro livro = new Livro();
                   livro.setId(rs.getInt("livro"));
                   emprestimo.setLivro(livro);

                   Usuario usuario = new Usuario();
                   usuario.setId(rs.getInt("usuario"));
                   emprestimo.setUsuario(usuario);

                   emprestimo.setDataEmprstimo(rs.getDate("dataEmprestimo").toLocalDate());

                   Date dataDev = rs.getDate("dataDevolucao");
                   if (dataDev != null){
                       emprestimo.setDataDevolucao(dataDev.toLocalDate());
                   }
               }
           }
        return emprestimo;
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo, int idOriginal)throws SQLException{
           String sql = """
                   UPDATE emprestimo
                   SET
                   livro = ?
                   usuario = ?
                   dataEmprestimo = ?
                   dataDevolucao = ?
                   WHERE id = ?
                   """;

           try (Connection conn = Conexao.connection();
           PreparedStatement psUPD = conn.prepareStatement(sql)){
               psUPD.setInt(1,emprestimo.getId());
               psUPD.setInt(2,emprestimo.getLivro().getId());
               psUPD.setInt(3,emprestimo.getUsuario().getId());
               psUPD.setDate(4,Date.valueOf(emprestimo.getDataEmprstimo()));
               psUPD.setDate(5,Date.valueOf(emprestimo.getDataDevolucao()));
               psUPD.executeUpdate();

           }
        return emprestimo;
    }

    public void deletarEmprestimo(int id)throws SQLException{
        String slq = """
                DALETE FROM emprestimo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.connection();
        PreparedStatement psDEL = conn.prepareStatement(slq)){
            psDEL.setInt(1,id);
            psDEL.executeUpdate();
        }
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimo;
    }
}
