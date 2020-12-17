package ch.hegarc.ig.business;

import java.util.LinkedList;
import java.util.List;

public class Donateur {
	private long id;
	private String prenom;
	private String nom;
	private String email;
	private String langue;
	private String adresse;
	private String ville;
	private String monnaie;
	private long somme;
	private boolean paye;
	private boolean annule;
	private String dateDon;
	private String dateVersement;

	/*********************
	 * Les constructeurs *
	 *********************/

	public Donateur () {
	}

	public Donateur (String prenom, String nom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public Donateur (String prenom, String nom, long somme) {
		this (prenom, nom);
		this.somme = somme;
		this.monnaie = "CHF";
	}

	public Donateur (long id, String prenom, String nom, String monnaie, long somme) {
		this (prenom, nom);
		this.id = id;
		this.monnaie = monnaie;
		this.somme = somme;
	}

	public Donateur (long id, String prenom, String nom, String monnaie, long somme, boolean paye, boolean annule) {
		this (id, prenom, nom, monnaie, somme);
		this.paye = paye;
		this.annule = annule;
	}

	public Donateur (long id, String prenom, String nom, String email, String langue, String adresse, String ville, String monnaie, long somme, boolean paye, boolean annule, String dateDon, String dateVersement) {
		this (id, prenom, nom, monnaie, somme, paye, annule);
		this.email = email;
		this.langue = langue;
		this.adresse = adresse;
		this.ville = ville;
		this.dateDon = dateDon;
		this.dateVersement = dateVersement;
	}

	public static List <Donateur> newPopDonateurs () {
		List <Donateur> d = new LinkedList <> ();
		Donateur d0 = new Donateur (95, "Jain", "Meylan", "CHF", 1);
		Donateur d1 = new Donateur (96, "Jain", "Meylan", "CHF", 11);
		Donateur d2 = new Donateur (97, "Quentin", "Boillat", "CHF", 100);
		Donateur d3 = new Donateur (98, "Stan", "Federer", "CHF", 876543);
		d.add (d0);
		d.add (d1);
		d.add (d2);
		d.add (d3);
		return d;
	}

	/*******************************
	 * Les getteres et les setters *
	 *******************************/

	public long getId () {
		return id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public String getPrenom () {
		return prenom;
	}

	public void setPrenom (String prenom) {
		this.prenom = prenom;
	}

	public String getNom () {
		return nom;
	}

	public void setNom (String nom) {
		this.nom = nom;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getLangue () {
		return langue;
	}

	public void setLangue (String langue) {
		this.langue = langue;
	}

	public String getAdresse () {
		return adresse;
	}

	public void setAdresse (String adresse) {
		this.adresse = adresse;
	}

	public String getVille () {
		return ville;
	}

	public void setVille (String ville) {
		this.ville = ville;
	}

	public String getMonnaie () {
		return monnaie;
	}

	public void setMonnaie (String monnaie) {
		this.monnaie = monnaie;
	}

	public long getSomme () {
		return somme;
	}

	public void setSomme (long somme) {
		this.somme = somme;
	}

	public boolean isPaye () {
		return paye;
	}

	public void setPaye (boolean paye) {
		this.paye = paye;
	}

	public boolean isAnnule () {
		return annule;
	}

	public void setAnnule (boolean annule) {
		this.annule = annule;
	}

	public String getDateDon () {
		return dateDon;
	}

	public void setDateDon (String dateDon) {
		this.dateDon = dateDon;
	}

	public String getDateVersement () {
		return dateVersement;
	}

	public void setDateVersement (String dateVersement) {
		this.dateVersement = dateVersement;
	}

	/**********
	 * Autres *
	 **********/

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append (this.nom).append (", ").append (this.prenom).append (" (id : ").append (this.id).append (")").append (". Montant : ").append (this.somme).append (" ").append (this.monnaie);
		return sb.toString ();
	}

	public boolean equals (Donateur donateur) {
		return this.nom.equalsIgnoreCase (donateur.getNom ()) && this.prenom.equalsIgnoreCase (donateur.getPrenom ());
	}
}