package ch.hegarc.ig.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// Classe implémentée par Comparable pour permettre aux projets d'être toujours classés par ordre alphabétique
public class Projet implements Comparable <Projet> {
	private long id;
	private String projet;
	private List <Donateur> donateurs = new ArrayList <Donateur> ();

	/*********************
	 * Les constructeurs *
	 *********************/

	public Projet () {
	}

	/**
	 * @param donateurs
	 * @param name
	 * @param id
	 */
	public Projet (long id, String name, List <Donateur> donateurs) {
		super ();
		this.id = id;
		this.projet = name;
		setDonateurs (donateurs);
		this.triDonateursNomPrenom (); // Tri systématique des donateurs
	}

	/**
	 * Pour peupler en dur le programme avec des projets
	 *
	 * @return
	 */
	public static List <Projet> newPopProjets () {
		List <Projet> projets = new LinkedList <> ();
		List <Donateur> donateurs = Donateur.newPopDonateurs ();
		List <Donateur> donateurs1 = Donateur.newPopDonateurs ();
		donateurs1.remove (1);
		donateurs.remove (0);
		Projet p1 = new Projet (90, "Haute Ecole de ConGestion", donateurs);
		Projet p2 = new Projet (91, "Voiture", donateurs1);
		projets.add (p2);
		projets.add (p1);
		return projets;
	}

	@Override // Les projets seront "Toujours" classés par ordre alphabétique
	public int compareTo (Projet p) {
		return this.getProjet ().compareTo (p.getProjet ());
	}

	/*************************
	 * Fonctions spécifiques *
	 *************************/

	public void triDonateursNomPrenom () {
		this.donateurs = this.donateurs.stream ().sorted (Comparator.comparing (Donateur::getNom).thenComparing (Donateur::getPrenom)).collect (Collectors.toList ());
	}

	public void triDonateursID () {
		this.donateurs = this.donateurs.stream ().sorted (Comparator.comparing (Donateur::getId)).collect (Collectors.toList ());
	}

	public void addDonateurs (List <Donateur> donateurs) {
		for (Donateur d : donateurs)
			addDonateur (d);
	}

	public boolean addDonateur (Donateur donateur) {
		boolean present = false;
		for (Donateur d : this.donateurs) { // On parcourt la liste pour savoir si le donateur existe déjà
			if (d.equals (donateur))
				present = true;
		}
		if (! present) // Si le donateur n'existe pas
			this.donateurs.add (donateur);
		triDonateursNomPrenom (); // Tri systématique des donateurs
		return ! present;
	}

	public void removeDonateur (Donateur donateur) {
		this.donateurs = this.donateurs.stream ().filter (donateur1 -> ! (donateur1.getPrenom ().equalsIgnoreCase (donateur.getPrenom ()) && donateur1.getNom ().equalsIgnoreCase (donateur.getNom ()))).collect (Collectors.toList ());
	}

	/**************************
	 * Les getters et Setters *
	 **************************/

	public long getId () {
		return id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public String getProjet () {
		return projet;
	}

	public void setProjet (String projet) {
		this.projet = projet;
	}

	public List <Donateur> getDonateurs () {
		return donateurs;
	}

	/**
	 * @param donateurs Vérifie dans la liste si un donateur est à double
	 */
	public void setDonateurs (List <Donateur> donateurs) {
		for (int i = 0 ; i < donateurs.size () ; i++) {
			for (int j = i + 1 ; j < donateurs.size () ; j++) {
				if (donateurs.get (i).equals (donateurs.get (j)))
					donateurs.remove (j);
			}
		}
		this.donateurs = donateurs;
	}

	/**********
	 * Autres *
	 **********/

	public String toString (boolean avecDonateurs) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("Nom projet : ").append (this.projet).append (" (id : ").append (this.id).append (")\n");
		if (avecDonateurs) {
			if (this.donateurs.isEmpty ())
				System.out.println ("Pas de donateurs pour ce projet");
			else {
				sb.append ("Les donateurs de ce projet : \n");
				for (Donateur d : this.donateurs)
					sb.append (d.toString ()).append ("    Montant : ").append (d.getSomme ()).append (" ").append (d.getMonnaie ()).append ("\n");
			}
		}
		return sb.toString ();
	}
}