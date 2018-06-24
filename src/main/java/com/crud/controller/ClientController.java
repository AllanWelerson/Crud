package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crud.models.Client;
import com.crud.repository.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository cr;
	
	@PostMapping
	public String saveClients(Client client) {
		
		this.cr.save(client);
		
		return "redirect:/clients";
	}
	
	@GetMapping
	public ModelAndView showClients()	{
		ModelAndView mv = new ModelAndView("list-clients"); 
		
		mv.addObject("clients",cr.findAll());
		mv.addObject("client",new Client());
		
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
	public String remove(@PathVariable Long id) {
		cr.delete(id);
		return "redirect:/clients";
	}
	
}
