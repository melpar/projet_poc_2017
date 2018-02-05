package com.project.poc.shared.bean;

import com.project.poc.shared.annotation.Id;
import com.project.poc.shared.annotation.Table;

@Table(name="T_PERSONNE_PER")
public class Personne {
	private String PER_nom;
	private String PER_prenom;
	private boolean PER_risque;
	
	@Id
	private String PER_id;
	
	public String getPER_nom() {
		return PER_nom;
	}
	public void setPER_nom(String pER_nom) {
		PER_nom = pER_nom;
	}
	public String getPER_prenom() {
		return PER_prenom;
	}
	public void setPER_prenom(String pER_prenom) {
		PER_prenom = pER_prenom;
	}
	public boolean isPER_risque() {
		return PER_risque;
	}
	public void setPER_risque(boolean pER_risque) {
		PER_risque = pER_risque;
	}
	public String getPER_id() {
		return PER_id;
	}
	public void setPER_id(String pER_id) {
		PER_id = pER_id;
	}
}
