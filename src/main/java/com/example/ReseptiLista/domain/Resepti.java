package com.example.ReseptiLista.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.List;


@Entity
public class Resepti {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
Long reseptiId;
String title;
String ainekset;
String reseptiText;


@Column(length = 50000000)
@JsonIgnore
private byte[] picByte;

@ManyToOne
@JsonIgnoreProperties("reseptit")
@JoinColumn(name = "ruokalajiId")
private Ruokalaji ruokalaji;

@OneToMany(mappedBy = "resepti", cascade = CascadeType.ALL)
@JsonIgnoreProperties("resepti")
private List<Kommentti> kommentit;



public Resepti() {
	
}



public Resepti(String title, String ainekset, String reseptiText, byte[] picByte, Ruokalaji ruokalaji) {
	super();
	this.title = title;
	this.ainekset = ainekset;
	this.reseptiText = reseptiText;
	this.picByte = picByte;
	this.ruokalaji = ruokalaji;
	
}



public byte[] getPicByte() {
	return picByte;
}

public void setPicByte(byte[] picByte) {
	this.picByte = picByte;
}





public String getAinekset() {
	return ainekset;
}



public void setAinekset(String ainekset) {
	this.ainekset = ainekset;
}



public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}

public Long getReseptiId() {
	return reseptiId;
}

public void setReseptiId(Long reseptiId) {
	this.reseptiId = reseptiId;
}
public String getReseptiText() {
	return reseptiText;
}
public void setReseptiText(String reseptiText) {
	this.reseptiText = reseptiText;
}

public Ruokalaji getRuokalaji() {
	return ruokalaji;
}

public void setRuokalaji(Ruokalaji ruokalaji) {
	this.ruokalaji = ruokalaji;
}



public List<Kommentti> getKommentit() {
	return kommentit;
}



public void setKommentit(List<Kommentti> kommentit) {
	this.kommentit = kommentit;
}









}
