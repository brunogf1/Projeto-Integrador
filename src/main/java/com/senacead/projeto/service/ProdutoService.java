
package com.senacead.projeto.service;

import com.senacead.projeto.model.Produto;
import com.senacead.projeto.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
   
    @Autowired
    ProdutoRepository produtoRepository;
    
    public Produto criar(Produto pro) {
        pro.setId(null);
        produtoRepository.save(pro);
        return pro;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    public Produto buscarPorId(Integer id){
        return produtoRepository.findById(id).orElseThrow();
    }
    
     public void excluir(Integer id){
         Produto produtoEncontrado = buscarPorId(id);
         produtoRepository.deleteById(produtoEncontrado.getId());
     }
     
     public Produto atualizar(Integer id, Produto produtoEnviado){
         Produto produtoEncontrado = buscarPorId(id);
         produtoEncontrado.setNome(produtoEnviado.getNome());
         produtoEncontrado.setDescricao(produtoEnviado.getDescricao());
         produtoEncontrado.setValor(produtoEnviado.getValor());
         produtoRepository.save(produtoEncontrado);
         return produtoEncontrado;
     }
    
}
