package com.example.biblioteca.Service;

import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.Repository.LivroRepository;

import java.sql.SQLException;
import java.util.List;

public class LivroService {
    LivroRepository livroRepository = new LivroRepository();


    public Livro CadastrarLivro(Livro livro)throws SQLException{
        if(livro.getAnoPublicacao() <= 0){
            throw new RuntimeException("Ano não pode ser menor que zero");
        }

        return livro;
    }


    public List<Livro> ListarTodosOsLivros()throws SQLException{
        List<Livro> livros = livroRepository.ListarLivros();
        if(livros.isEmpty()){
            throw new RuntimeException("A lista esta vazia");
        }
        return livros;
    }

    public Livro BuscarLivroPorID(int id)throws SQLException{
            Livro livro = livroRepository.BuscarPorId(id);
            if(livro == null){
                throw new RuntimeException("Livro não encontrado");
            }

        return livro;
    }

    public Livro AtualizarLivro(Livro livro)throws SQLException{
        Livro livros = livroRepository.atualizarLivro(livro);
            if(livros == null){
                throw new RuntimeException("Livro não encontrado");
        }

        return livroRepository.atualizarLivro(livro);
    }

    public void DeletarLivro(int id)throws SQLException{
        Livro livros = livroRepository.BuscarPorId(id);

        if(livros == null){
            throw new RuntimeException("Livro não encontrado");
        }

        livroRepository.delete(id);
    }

    public Livro AtualizarLivro(Livro livro, int id) {
        return livro;
    }
}
