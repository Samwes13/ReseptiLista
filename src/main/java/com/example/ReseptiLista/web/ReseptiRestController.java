package com.example.ReseptiLista.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.service.ReseptiService;

@CrossOrigin // Sallii ristikkäiset pyynnöt (esim. jos frontend ja backend ovat eri palvelimilla)
@RestController
public class ReseptiRestController {

    @Autowired
    private ReseptiService reseptiService;

    // Hae kaikki reseptit
    @GetMapping("/api/reseptit")
    public List<Resepti> getAllReseptit() {
        return (List<Resepti>) reseptiService.getAllReseptit();
    }

    // Hae yksittäinen resepti ID:n perusteella ja kommentit
    @GetMapping("/api/resepti/{id}")
    public ResponseEntity<Resepti> getReseptiById(@PathVariable("id") Long id) {
        Resepti resepti = reseptiService.getReseptiById(id);
        if (resepti != null) {
            return ResponseEntity.ok(resepti);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Lisää muita REST-metodeja tarvittaessa...
}
