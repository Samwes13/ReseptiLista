package com.example.ReseptiLista;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ReseptiLista.ReseptiListaApplication;
import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.domain.ReseptiRepository;
import com.example.ReseptiLista.domain.Ruokalaji;
import com.example.ReseptiLista.domain.RuokalajiRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReseptiRepositoryTest {

    @Autowired
    private ReseptiRepository reseptiRepository;

    @Autowired
    private RuokalajiRepository ruokalajiRepository;

    @Test
    public void createNewResepti() {
        Ruokalaji ruokalaji = ruokalajiRepository.save(new Ruokalaji("TestRuokalaji"));
        Resepti resepti = new Resepti("Testiresepti", "Ainekset", "Ohjeet", null, ruokalaji);
        reseptiRepository.save(resepti);
        assertThat(resepti.getReseptiId()).isNotNull();
    }

    @Test
    public void findByTitleWhenReseptiExists() {
        List<Resepti> reseptit = reseptiRepository.findByTitle("Lihapullat");
        assertThat(reseptit).isNotEmpty();
        assertThat(reseptit.get(0).getTitle()).isEqualTo("Lihapullat");
    }

    @Test
    public void deleteResepti() {
        List<Resepti> reseptit = reseptiRepository.findByTitle("Testiresepti");
        assertThat(reseptit).isNotEmpty();
        Resepti reseptiToDelete = reseptit.get(0);
        reseptiRepository.delete(reseptiToDelete);
        List<Resepti> deletedReseptit = reseptiRepository.findByTitle("Testiresepti");
        assertThat(deletedReseptit).isEmpty();
    }
}
