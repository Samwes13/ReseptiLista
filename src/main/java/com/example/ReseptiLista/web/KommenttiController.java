package com.example.ReseptiLista.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ReseptiLista.domain.Kommentti;
import com.example.ReseptiLista.domain.KommenttiRepository;
import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.service.ReseptiService;

@Controller
public class KommenttiController {
	
	@Autowired
    private ReseptiService reseptiService;
	
	@Autowired
    private KommenttiRepository kommenttiRepository;

    // Listaa kaikki kommentit tietylle reseptille
    @GetMapping("/resepti/{reseptiId}/kommentit")
    public String showCommentsForResepti(@PathVariable Long reseptiId, Model model) {
        List<Kommentti> kommentit = (List<Kommentti>) kommenttiRepository.findByReseptiReseptiId(reseptiId);
        model.addAttribute("kommentit", kommentit);
        model.addAttribute("reseptiId", reseptiId);
        return "resepti"; 
    }

    // Nayttaa lomakkeen uuden kommentin lisäämiseksi
    @GetMapping("/addKommentti/{reseptiId}")
    public String addKommenttiForm(@PathVariable Long reseptiId, Model model) {
        Kommentti uusiKommentti = new Kommentti();
        model.addAttribute("kommentti", uusiKommentti);
        model.addAttribute("reseptiId", reseptiId);
        return "kommentti"; 
    }
    
    //Tallentaa tietyn kommentin tietylle reseptille
    @PostMapping("/saveKommentti/{reseptiId}")
    public String saveKommentti(@PathVariable Long reseptiId, @RequestParam("kommenttiText") String kommenttiTeksti) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        Kommentti uusiKommentti = new Kommentti(kommenttiTeksti, resepti);
        kommenttiRepository.save(uusiKommentti);
        return "redirect:/resepti/" + reseptiId;
    }



	
}