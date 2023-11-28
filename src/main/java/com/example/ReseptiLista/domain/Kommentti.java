package com.example.ReseptiLista.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kommentti {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
Long kommenttiId;
String kommenttiText;

@ManyToOne
@JsonIgnoreProperties("kommentti")
@JoinColumn(name = "reseptiId")
private Resepti resepti;

public Kommentti(String kommenttiText, Resepti resepti) {
	super();
	this.kommenttiText = kommenttiText;
	this.resepti = resepti;
}

public Kommentti() {
	
}

public Long getKommenttiId() {
	return kommenttiId;
}

public void setKommenttiId(Long kommenttiId) {
	this.kommenttiId = kommenttiId;
}



public String getKommenttiText() {
	return kommenttiText;
}

public void setKommenttiText(String kommenttiText) {
	this.kommenttiText = kommenttiText;
}

public Resepti getResepti() {
	return resepti;
}

public void setResepti(Resepti resepti) {
	this.resepti = resepti;
}

	

}
