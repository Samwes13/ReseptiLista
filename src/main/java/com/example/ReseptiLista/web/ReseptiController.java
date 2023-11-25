package com.example.ReseptiLista.web;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.domain.ReseptiRepository;
import com.example.ReseptiLista.domain.Ruokalaji;
import com.example.ReseptiLista.domain.RuokalajiRepository;
import com.example.ReseptiLista.service.ReseptiService;



@Controller
public class ReseptiController {

    @Autowired
    private ReseptiService reseptiService;
    
    @GetMapping("/etusivu")
    public String viewReseptiList(@RequestParam(required = false) Long ruokalajiId, Model model) {
        Iterable<Resepti> reseptit;
        if (ruokalajiId != null) {
            reseptit = reseptiService.getReseptitByRuokalaji(ruokalajiId);
        } else {
            reseptit = reseptiService.getAllReseptit();
        }
        model.addAttribute("reseptit", reseptit);

        // Lisää myös kaikki ruokalajit malliin
        Iterable<Ruokalaji> ruokalajit = reseptiService.getAllRuokalajit();
        model.addAttribute("ruokalajit", ruokalajit);

        return "etusivu";
    }
    
    
    @GetMapping("/ruokalaji/{ruokalajiId}")
    public String viewReseptitByRuokalaji(@PathVariable Long ruokalajiId, Model model) {
        Iterable<Resepti> reseptit = reseptiService.getReseptitByRuokalaji(ruokalajiId);
        model.addAttribute("reseptit", reseptit);

        // Lisää myös kaikki ruokalajit malliin, jos haluat näyttää valikon
        Iterable<Ruokalaji> ruokalajit = reseptiService.getAllRuokalajit();
        model.addAttribute("ruokalajit", ruokalajit);

        return "ruokalajiView"; // Olettaen, että sinulla on erillinen näkymä tälle
    }
    
    @GetMapping("/deleteResepti/{reseptiId}")
    public String deleteResepti(@PathVariable Long reseptiId) {
        reseptiService.deleteResepti(reseptiId);
        return "redirect:/etusivu";
    }
    
    @GetMapping("/editResepti/{reseptiId}")
    public String showEditForm(@PathVariable Long reseptiId, Model model) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        model.addAttribute("resepti", resepti);
        return "editResepti"; // Olettaen, että sinulla on erillinen näkymä "editResepti.html"
    }

    @PostMapping("/updateResepti/{reseptiId}")
    public String updateResepti(@PathVariable Long reseptiId, @ModelAttribute Resepti resepti, 
                                @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            resepti.setPicByte(file.getBytes());
        }
        reseptiService.saveResepti(resepti);
        return "redirect:/etusivu";
    }
    
    @GetMapping("/images/{reseptiId}")
    public ResponseEntity<byte[]> getReseptiImage(@PathVariable Long reseptiId) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        byte[] imageBytes = resepti.getPicByte();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // Näyttää lomakkeen uuden reseptin lisäämiseksi
    @GetMapping("/addresepti")
    public String addReseptiForm(Model model) {
        model.addAttribute("resepti", new Resepti());
        
        Iterable<Ruokalaji> ruokalajit = reseptiService.getAllRuokalajit();
        model.addAttribute("ruokalajit", ruokalajit);
        
        return "addresepti";
    }
    
    @GetMapping("/resepti/{reseptiId}")
    public String viewResepti(@PathVariable Long reseptiId, Model model) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        model.addAttribute("resepti", resepti);
        return "resepti";
    }


    // Tallentaa uuden reseptin ja kuvan
    @PostMapping("/save")
    public String saveResepti(@ModelAttribute Resepti resepti, @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            resepti.setPicByte(file.getBytes());
        }
        reseptiService.saveResepti(resepti);
        return "redirect:/etusivu"; // ohjaa käyttäjän reseptilista-sivulle tallennuksen jälkeen
    }

    // Lisää muita metodeja tarvittaessa...

}




