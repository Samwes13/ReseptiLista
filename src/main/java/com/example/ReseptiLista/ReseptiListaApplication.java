package com.example.ReseptiLista;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ReseptiLista.domain.Resepti;
import com.example.ReseptiLista.domain.ReseptiRepository;
import com.example.ReseptiLista.domain.Ruokalaji;
import com.example.ReseptiLista.domain.RuokalajiRepository;
import com.example.ReseptiLista.domain.AppUserRepository;
import com.example.ReseptiLista.service.ReseptiService;
import com.example.ReseptiLista.domain.AppUser;

@SpringBootApplication
public class ReseptiListaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReseptiListaApplication.class, args);
	}
	
@Bean

public CommandLineRunner demo(ReseptiRepository reseptirepository, RuokalajiRepository lajirepository, ReseptiService reseptiService, AppUserRepository auRepository) {
	return (arg) -> {
		
		lajirepository.save(new Ruokalaji("Pääruoka"));
		lajirepository.save(new Ruokalaji("Jälkiruoka"));
		lajirepository.save(new Ruokalaji("Välipala"));
		lajirepository.save(new Ruokalaji("Leivonta"));
		
		//Path kuvaPolku = Paths.get(ClassLoader.getSystemResource("static/images/lihapulla.jpg").toURI());
		//byte[] kuva = Files.readAllBytes(kuvaPolku);

		// Create users with BCrypt encoded password (user/user, admin/admin)
					AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
					AppUser user2 = new AppUser("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
					auRepository.saveAll(Arrays.asList(user1, user2));
		
		
	};
}

}
