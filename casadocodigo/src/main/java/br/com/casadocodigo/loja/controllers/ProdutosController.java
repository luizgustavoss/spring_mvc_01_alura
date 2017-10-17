package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping("/form")
	public ModelAndView form(){	
		ModelAndView mav = new ModelAndView("produtos/form").addObject("tiposPrecos", TipoPreco.values());		
		return mav;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String gravar(Produto produto){
		System.out.println(produto);
		dao.gravar(produto);		
		return "produtos";
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listagem(){
		
		List<Produto> produtos = dao.listar();
		ModelAndView mav = new ModelAndView("produtos/lista").addObject("produtos", produtos);		
		return mav;
	}

}
