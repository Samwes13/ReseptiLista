package com.example.ReseptiLista;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.domain.ReseptiRepository;
import com.example.ReseptiLista.domain.Ruokalaji;
import com.example.ReseptiLista.domain.RuokalajiRepository;
import com.example.ReseptiLista.service.ReseptiService;

@SpringBootApplication
public class ReseptiListaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReseptiListaApplication.class, args);
	}
	
@Bean

public CommandLineRunner demo(ReseptiRepository reseptirepository, RuokalajiRepository lajirepository, ReseptiService reseptiService) {
	return (arg) -> {
		
		lajirepository.save(new Ruokalaji("Pääruoka"));
		lajirepository.save(new Ruokalaji("Jälkiruoka"));
		lajirepository.save(new Ruokalaji("Välipala"));
		lajirepository.save(new Ruokalaji("Leivonta"));
		
		Path kuvaPolku = Paths.get(ClassLoader.getSystemResource("static/images/lihapulla.jpg").toURI());
		byte[] kuva = Files.readAllBytes(kuvaPolku);

	//	reseptirepository.save(new Resepti("Lihapullat", "reseptitext1", kuva));
		//reseptitext1.add(new reseptitext());
	};
}

}
