package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProdutoValidation());
	}
	
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto){	
		
		ModelAndView mav = new ModelAndView("produtos/form").addObject("tiposPrecos", TipoPreco.values());		
		return mav;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="produtos.home", allEntries=true) // limpa um cache especificado
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos_sumario", sumario);
		produto.setSumarioPath(path);
		
		dao.gravar(produto);		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:produtos");
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
		
		List<Produto> produtos = dao.listar();
		ModelAndView mav = new ModelAndView("produtos/lista").addObject("produtos", produtos);		
		return mav;
	}
	
	
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id){
		
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = dao.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Produto detalheJson(@PathVariable("id") Integer id){
		
		return dao.find(id);
	}
	
	
	@ExceptionHandler(NoResultException.class)
	public String tratamentoDeErro(){
		
		return "erro";
	}

}
