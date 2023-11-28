package com.example.ReseptiLista.service;

import com.example.ReseptiLista.domain.Kommentti;
import com.example.ReseptiLista.domain.KommenttiRepository;
import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.domain.ReseptiRepository;
import com.example.ReseptiLista.domain.Ruokalaji;
import com.example.ReseptiLista.domain.RuokalajiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseptiService {

    @Autowired
    private ReseptiRepository reseptiRepository;
    
    @Autowired
    private RuokalajiRepository ruokalajiRepository;
    
    @Autowired
    private KommenttiRepository kommenttiRepository;

    // Tallentaa reseptin ja siihen liittyvän kuvan
    public void saveResepti(Resepti resepti) {
        reseptiRepository.save(resepti);
    }
    
    public Iterable<Resepti> getAllReseptit() {
        return reseptiRepository.findAll();
    }
    
    public Resepti getReseptiById(Long reseptiId) {
        // findById palauttaa Optional<Resepti>, joten käytä orElseThrow saadaksesi Resepti-objektin tai heitä poikkeus, jos sitä ei löydy.
        return reseptiRepository.findById(reseptiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resepti ID: " + reseptiId));
    }
    
    public Iterable<Ruokalaji> getAllRuokalajit() {
        return ruokalajiRepository.findAll();
    }
    
    
    public Iterable<Resepti> getReseptitByRuokalaji(Long ruokalajiId) {
        return reseptiRepository.findByRuokalajiRuokalajiId(ruokalajiId);
    }

    
    public void deleteResepti(Long reseptiId) {
        reseptiRepository.deleteById(reseptiId);
    }
    
 // Tallenna uusi kommentti
    public Kommentti saveKommentti(Kommentti kommentti) {
        return kommenttiRepository.save(kommentti);
    }

    // Hae kaikki kommentit tietylle reseptille
    public Iterable<Kommentti> getKommentitByResepti(Long reseptiId) {
        return kommenttiRepository.findByReseptiReseptiId(reseptiId);
    }
    
    // Voit lisätä muita metodeja, kuten reseptin haku, päivitys, poisto jne.

}
