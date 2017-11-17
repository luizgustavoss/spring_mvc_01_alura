package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {

	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/")
	@Cacheable(value="produtos.home") // Habilitação do chache na aplicação, definindo um nome único de cache
    public ModelAndView index(){

		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelandView = new ModelAndView("home");
		modelandView.addObject("produtos", produtos);
		
		return modelandView;
    }
}
