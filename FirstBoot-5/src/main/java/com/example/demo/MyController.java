package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@Autowired
	JdbcTemp p1;
	
	@GetMapping("/form")
	public String getForm() {
		
		return "form";
	}
	@ResponseBody
	@PostMapping("/submit")
	public String gestisciForm(@RequestParam("nome") String nome, 
			@RequestParam("marca") String marca,
			@RequestParam("prezzo") double prezzo,
			@RequestParam("url") String url
			) {
		
		p1.insertGame(nome, marca, prezzo, url);
				return nome + " aggiunto con successo";
		
		
		
		
	}
	
	@ResponseBody
	@PostMapping("/update")
	public String updatePrezzo(@RequestParam("nome") String nome,
			@RequestParam("prezzo") double prezzo
			) {
		p1.setPrezzo(nome, prezzo);
		return nome + " aggiornato con successo";
		
	}

}
