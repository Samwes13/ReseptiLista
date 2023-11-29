package com.example.ReseptiLista.web;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.ReseptiLista.domain.Kommentti;


@Controller
public class ReseptiController {

    @Autowired
    private ReseptiService reseptiService;
    
    //Metodi joka maarittelee polun, jolla tama aktivoidaan
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    
    //Kasittelee GET-pyynnon"/etusivu"-osoitteeseen. Näyttää reseptilistan
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
    
    //Delete tominto, jolla on ROLE_ADMIN
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/deleteResepti/{reseptiId}")
    public String deleteResepti(@PathVariable Long reseptiId) {
        reseptiService.deleteResepti(reseptiId);
        return "redirect:/etusivu";
    }
    
    //EDIT toiminto, joka nayttaa tietyn reseptin muokkaamisen
    @GetMapping("/editResepti/{reseptiId}")
    public String showEditForm(@PathVariable Long reseptiId, Model model) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        model.addAttribute("resepti", resepti);
        Iterable<Ruokalaji> ruokalajit = reseptiService.getAllRuokalajit();
        model.addAttribute("ruokalajit", ruokalajit);
        
        return "editResepti"; // 
    }
    
    //Paivittaa reseptin tietokantaan
    @PostMapping("/updateResepti/{reseptiId}")
    public String updateResepti(@PathVariable Long reseptiId, @ModelAttribute Resepti resepti, 
                                @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            resepti.setPicByte(file.getBytes());
        }
        reseptiService.saveResepti(resepti);
        return "redirect:/etusivu";
    }
    
    //Palauttaa reseptiin liittyvan kuvan
    @GetMapping("/images/{reseptiId}")
    public ResponseEntity<byte[]> getReseptiImage(@PathVariable Long reseptiId) {
        Resepti resepti = reseptiService.getReseptiById(reseptiId);
        byte[] imageBytes = resepti.getPicByte();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // Nayttaa lomakkeen uuden reseptin lisaamiseksi
    @GetMapping("/addresepti")
    public String addReseptiForm(Model model) {
        model.addAttribute("resepti", new Resepti());
        
        Iterable<Ruokalaji> ruokalajit = reseptiService.getAllRuokalajit();
        model.addAttribute("ruokalajit", ruokalajit);
        
        return "addresepti";
    }
    
    //Nayttaa yksittaisen reseptin
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
        return "redirect:/etusivu"; // ohjaa kayttajan reseptilista-sivulle tallennuksen jalkeen
    }
    
    
    
    
    
    

}




