package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;

@Controller
public class MyController {
	
	@Autowired
	JdbcTemp p1;
	
	@GetMapping("/form")
	public String getForm(Model model) {
		
		ArrayList <gioco> lista = p1.getLista();
		
		model.addAttribute("lista", lista);
		
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
	@ResponseBody
	@PostMapping("/delete")
	public String delete(@RequestParam("nome") String nome) {
		
		p1.delete(nome);
		
		
		return nome + "Cancellato con successo";
	}
	
	@GetMapping("/")
	public String getStore(Model model) {
ArrayList <gioco> lista = p1.getLista();
		
		model.addAttribute("lista", lista);
		return "store";
	}
	
	@PostMapping("/buy")
	public String recap(@RequestParam("selected") ArrayList<Integer> selected, Model model) {
		
		int somma = 0;
		ArrayList <gioco> lista = p1.getLista();
		ArrayList <giocoA> listaP = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			
			
			
			if (selected.get(i) > 0 ) {
			//System.out.println("Hai acquistato " + listaP.get(i).getNome() + " marca " + listaP.get(i).getMarca()  );
			//System.out.println("In " + selected.get(i) + " pezzi");
			somma += selected.get(i) * lista.get(i).getPrezzo();
			giocoA g1 = new giocoA(lista.get(i).getNome(), lista.get(i).getMarca(),(lista.get(i).getPrezzo()*selected.get(i)),
					lista.get(i).getUrl(), selected.get(i) );
			listaP.add(g1);
			p1.change(lista.get(i).getNome(), selected.get(i));
			
		}
		}
		
		//System.out.println("La somma totale da pagare è: " + somma + " euro");
		model.addAttribute("lista", listaP);
		model.addAttribute("somma", somma);
		return "recap";
	}

}
