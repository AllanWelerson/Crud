package com.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.models.Client;
import com.crud.repository.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository cr;
	
	@PostMapping
	public ModelAndView saveClients(@Valid Client client, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return  showClients(client);
		}
		this.cr.save(client);
		attributes.addFlashAttribute("message","Cliente salvo com sucesso!");		
		
		return new ModelAndView("redirect:/clients");
	}
	
	@GetMapping
	public ModelAndView showClients(Client client)	{
		ModelAndView mv = new ModelAndView("list-clients"); 
		
		mv.addObject("clients",cr.findAll());
		mv.addObject(client);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView select(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("list-clients");
		mv.addObject("client",cr.findOne(id));
		mv.addObject("clients",cr.findAll());
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String remove(@PathVariable Long id, RedirectAttributes attributes) {
		
		cr.delete(id);
		
		attributes.addFlashAttribute("message","Cliente removido com sucesso!");
		
		return "redirect:/clients";
	}
	
}
