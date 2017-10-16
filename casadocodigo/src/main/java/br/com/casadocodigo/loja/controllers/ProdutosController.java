package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping("/produtos/form")
	public String form(){		
		return "produtos/form";
	}
	
	
	@RequestMapping("/produtos")
	public String salvar(Produto produto){
		
		System.out.println(produto);
		dao.gravar(produto);		
		return "produtos/listagem";
	}
	
	
	@RequestMapping("/produtos/listagem")
	public String listagem(Produto produto){
		
		System.out.println(produto);
		
		return "produtos/listagem";
	}

}
