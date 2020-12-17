package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;

// Cette classe a été créée pour gérer le stockage des projets dans le programme
// Également créée pour les méthodes qui s'appliquent à TOUS les projets

public class ProjetUtil {
	private Set <Projet> projets;
	// Choix du Set car il ne pouvait pas y avoir de doublons, même si finalement cela concernait surtout le nom du projet pour y fusionner les donateurs...

	/*****************
	 * Constructeurs *
	 *****************/

	public ProjetUtil () {
		this.projets = new TreeSet <> ();
	}

	public ProjetUtil (Set <Projet> projets) {
		this.projets = projets;
	}

	/*************************
	 * Fonctions spécifiques *
	 *************************/

	/**
	 * @param donateurs Des noms séparés par une virgule (sans prénom, ni espace)
	 * @return la somme des dons (payé ou pas, annulé ou pas)
	 */
	public double totalDonsDonateurs (String donateurs) {
		double somme;
		List <Donateur> allDonateurs = allDonateurs (); // Stockage de tous les donateurs de tous les projets (facilite l'implémentation ci-dessous)
		Map <Donateur, Double> donateursMap = new HashMap <> (); // Choix du Map pour faire une somme des dons des donateurs dans Double
		String[] dona = donateurs.split (",");

		for (String s : dona) { // Parcourt tous les donateurs désirés
			for (Donateur d : allDonateurs) {
				if (s.equalsIgnoreCase (d.getNom ())) { // Si le donateur désiré existe dans les donateurs stockés
					if (donateursMap.containsKey (d)) { // Si le donateur a déjà été trouvé dans cette méthode
						somme = donateursMap.get (d); // On récupère la somme déjà engagée par le donateur dans ses autres dons
						donateursMap.replace (d, somme + d.getSomme ()); // On y ajoute la somme du don en cours
					} else
						donateursMap.put (d, (double) d.getSomme ()); // Si pas déjà présent, on ajoute le donateur ainsi que la somme du don en cours
				}
			}
		}
		return donateursMap.values ().stream ().reduce ((double) 0, Double::sum); // 0 pour l'élément neutre de l'addition
	}

	public boolean addDonateur (String projetName, String nom, String prenom, long somme) {
		Projet p = getProjet (projetName);
		if (p != null) // Si le projet existe
			return p.addDonateur (new Donateur (prenom, nom, somme));
		return false;
	}

	public void removeDonateur (String projetName, String nom, String prenom) {
		Projet p = getProjet (projetName);
		if (p != null) // Si le projet existe
			p.removeDonateur (new Donateur (prenom, nom));
	}

	public void addProjets (List <Projet> projets) {
		for (Projet p : projets)
			this.addProjet (p);
	}

	public boolean addProjet (Projet projet) {
		Projet p = getProjet (projet.getProjet ());
		if (p == null) { // Si le projet n'existe pas
			projet.triDonateursNomPrenom (); // Tri systématique des donateurs
			this.projets.add (projet);
			return true;
		} else { // Si le projet existe déjà, on fusionne les nouveaux et anciens donateurs
			p.addDonateurs (projet.getDonateurs ());
			return false;
		}
	}

	/**
	 * @param id
	 * @return null si n'existe pas
	 */
	public Projet getProjet (long id) {
		for (Iterator <Projet> it = this.projets.iterator () ; it.hasNext () ; ) {
			Projet p = it.next ();
			if (p.getId () == id)
				return p;
		}
		return null;
	}

	/**
	 * @param projetName
	 * @return null si n'existe pas
	 */
	public Projet getProjet (String projetName) {
		for (Iterator <Projet> it = this.projets.iterator () ; it.hasNext () ; ) {
			Projet p = it.next ();
			if (p.getProjet ().equalsIgnoreCase (projetName))
				return p;
		}
		return null;
	}

	public List <Projet> toList () {
		return new LinkedList <> (this.projets);
	}

	public List <Donateur> allDonateurs () {
		List <Donateur> donateurs = new LinkedList <> ();
		for (Projet p : this.projets)
			donateurs.addAll (p.getDonateurs ());
		return donateurs;
	}

	/******************************
	 * Les getters et les Setters *
	 ******************************/

	public Set <Projet> getProjets () {
		return projets;
	}

	public void setProjets (Set <Projet> projets) {
		this.projets = projets;
	}

	public int getSize () {
		return this.projets.size ();
	}

	/**********
	 * Autres *
	 **********/

	/**
	 * @param avecDonateurs Si false, retourne seulement id et le nom
	 * @return
	 */
	public String toString (boolean avecDonateurs) {
		StringBuilder sb = new StringBuilder ();
		for (Projet p : this.projets)
			sb.append (p.toString (avecDonateurs));
		return sb.toString ();
	}
}