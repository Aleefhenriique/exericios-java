package com.projeto.bean;


import com.projeto.model.Livro;
import com.projeto.service.LivroService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LivroBean implements Serializable {

    private Livro livro;
    private List<Livro> livros;

    @Inject
    private LivroService livroService;

    @PostConstruct
    public void init() {
        livro = new Livro();
        livros = livroService.listarLivros();
    }

    public void salvar(){
        livroService.salvarLivro(livro);
        livros = livroService.listarLivros();
        livro = new Livro();
    }

    public void atualizar(){
        livroService.AtualizarLivro(livro);
        livros = livroService.listarLivros();
    }

    public void remover(Long id) {
        livroService.removerLivro(id);
        livros = livroService.listarLivros();
    }

    public void emprestar(Long id){
        livroService.emprestarLivro(id);
        livros = livroService.listarLivros();
    }

    public void devolver(Long id){
        livroService.devolverLivro(id);
        livros = livroService.listarLivros();
    }
}
