package com.rafael.vinhos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafael.vinhos.model.TipoVinho;
import com.rafael.vinhos.model.Vinho;
import com.rafael.vinhos.repository.Vinhos;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	@Autowired
	private Vinhos vinhos;
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id){
		return novo(vinhos.findOne(id));
	}
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView model = new ModelAndView("vinhos/cadastro-vinho");
		//model.addObject("vinho", new Vinho());
		model.addObject(vinho);
		model.addObject("tipos", TipoVinho.values());
		return model;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult bind, RedirectAttributes attributes) {
		if (bind.hasErrors()) {
			return novo(vinho);
		}
		vinhos.save(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		return new ModelAndView("redirect:/vinhos/novo");
	}

}
