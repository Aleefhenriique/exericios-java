package com.projeto.service;

import com.projeto.model.Livro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Anotação para nao manter o estado entre as chamadas.
@Stateless
public class LivroService {

    //Anotação usada para injetar um EntitiyManager em uma classe gerenciada pelo container.
    @PersistenceContext
    private EntityManager em;

    public void salvarLivro(Livro livro) {
        livro.setStatus("Disponivel");
        em.persist(livro);
    }

    public List<Livro> listarLivros(){
        return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
    }

    public void AtualizarLivro(Livro livro) {
        em.merge(livro);
    }

    public void removerLivro(Long id) {
        Livro livro = em.find(Livro.class, id);
        if (livro != null) {
            em.remove(livro);
        }
    }

    public void emprestarLivro(Long id) {
        Livro livro = em.find(Livro.class, id);
        if(livro != null && "Disponivel".equals(livro.getStatus())) {
            livro.setStatus("Emprestado");
            em.merge(livro);
        }
    }

    public void devolverLivro(Long id) {
        Livro livro = em.find(Livro.class, id);
        if (livro != null && "Emprestado".equals(livro.getStatus())) {
            livro.setStatus("Disponivel");
            em.merge(livro);
        }
    }

}
