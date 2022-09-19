package br.com.modelo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.modelo.entity.Pessoa;
import br.com.modelo.repository.PessoaRepository;


@Controller
@RequestMapping
public class PessoaController {
    @Autowired
    private PessoaRepository _pessoaRepository;
    
    
    @Value("${spring.application.name}")
    String appName;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
 
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("pessoas", _pessoaRepository.findAll());
        return "home";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
    	System.out.println("Editou : " + id);
    	model.addAttribute("pessoa", _pessoaRepository.findById(id).get());
    	return "cadastroPessoa";
    }
    
    /***
  	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVA PESSOA NO SISTEMA
  	 * @param model
  	 * @return
  	 */
    @GetMapping(value="/addPessoa")	
  	public String novoCadastro(Model model) {
    	Pessoa p = new Pessoa();
  		model.addAttribute("pessoa", p);
  	    return"cadastroPessoa";
  	}
  	
  	/***
  	 * SALVA UM NOVO USUÁRIO NO SISTEMA
  	 * @param usuarioModel
  	 * @param result
  	 * @param model
  	 * @param redirectAttributes
  	 * @return
  	 */
  	@RequestMapping(value="/salvarPessoa", method= RequestMethod.POST)
  	public String salvarUsuario(@ModelAttribute @Valid Pessoa usuarioModel, 
  								final BindingResult result,
  								Model model,
  								RedirectAttributes redirectAttributes){
  		
  		_pessoaRepository.save(usuarioModel);
  		
  		 return "redirect:/";
  	}
    
    @GetMapping("/remover/{id}")
    public String remover(Model model, @PathVariable long id) {
    	_pessoaRepository.deleteById(id);
    	 return "redirect:/home";
    }
  	
}
