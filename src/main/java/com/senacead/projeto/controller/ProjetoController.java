package com.senacead.projeto.controller;

import com.senacead.projeto.model.Produto;
import com.senacead.projeto.repository.ProdutoRepository;
import com.senacead.projeto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjetoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("lista", produtoService.listarTodos());
        return "index";
    }

    @GetMapping("/logar")
    public String logar() {
        return "login";
    }

    @GetMapping("/config")
    public String config() {
        return "configuracoes";
    }

    @GetMapping("/inserir-produto")
    public String cadastroForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastro";
    }

    @GetMapping("/listagemA")
    public String listaAForm(Model model) {
        model.addAttribute("lista", produtoService.listarTodos());
        return "listaAtualizar";
    }

    @GetMapping("/listagemE")
    public String listaEForm(Model model) {
        model.addAttribute("lista", produtoService.listarTodos());
        return "listaExcluir";
    }

    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Produto produto, Model model) {

        if (produto.getId() != null) {
            produtoService.atualizar(produto.getId(), produto);
        } else {
            produtoService.criar(produto);
        }
        return "redirect:/config";
    }

    @GetMapping("/excluir")
    public String excluirProdutos(Model model, @RequestParam String id) {
        Integer idProduto = Integer.parseInt(id);
        produtoService.excluir(idProduto);
        return "redirect:/config";
    }

    @GetMapping("/alterar")
    public String alterarProduto(Model model, @RequestParam String id) {
        Integer idProduto = Integer.parseInt(id);
        Produto produtoEncontrado = produtoService.buscarPorId(idProduto);
        model.addAttribute("produto", produtoEncontrado);
        return "alterar";
    }

}
