package ch.hegarc.ig.util;

// Classe propre.

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;

// Cette classe a été créée pour gérer le stockage des projets dans le programme
// également créée pour les méthodes qui s'appliquent à tous les projets

public class ProjetUtil {
	private Set<Projet> projets;
	// Choix du Set car il ne pouvait pas y avoir de doublons, même si finalement cela concernait surtout le nom du projet pour y fusionner les donateurs...

	public ProjetUtil () {
		this.projets = new TreeSet <> ();
	}

	public ProjetUtil (Set <Projet> projets) {
		this.projets = projets;
	}

//	Fonctionne ! (Pas d'espaces dans donateurs. Gère les dons sur plusieurs projets d'un même donateur. Gère que les noms, pas les prénoms...)
	public double totalDonsDonateurs (String donateurs) {
		double somme;
		List<Donateur> allDonateurs = allDonateurs ();
		Map <Donateur, Double> donateursMap = new HashMap <> ();
		List<String> dona = Arrays.asList (donateurs.split (","));

		for (String s : dona) { // Parcours tous les donateurs désirés
			for (Donateur d : allDonateurs) { // Parcours tous les donateurs stockés
				if (s.equalsIgnoreCase (d.getNom ())) { // Si le donateur désiré existe
					if (donateursMap.containsKey (d)) { // Si le donateur a déjjà été trouvé dans cette méthode
						somme = donateursMap.get (d); // On récupère la somme déjà engagée par le donateur
						donateursMap.replace (d, somme + d.getSomme ()); // On y ajoute la somme du don en cours
					} else
						donateursMap.put (d, (double) d.getSomme ()); // Si pas déjà présent, on ajoute la somme du don en cours
				}
			}
		}
		return donateursMap.values ().stream ().reduce ((double) 0, Double::sum);
	}

//	Fonctionne !
	public boolean addDonateur (String projetName, String nom, String prenom, long somme) {
		Projet p = getProjet (projetName);
		if (p != null)
			return p.addDonateur (new Donateur (nom, prenom, somme));
		return false;
	}

//	Fonctionne !
	public void removeDonateur (String projetName, String nom, String prenom) {
		Projet p = getProjet (projetName);
		if (p != null)
			p.removeDonateur (new Donateur (nom, prenom));
	}

//	Fonctionne !
	public List<Donateur> pasEncorePaye () {
		List<Donateur> donateurs = new LinkedList <> ();
		for (Projet p : this.projets) {
			List<Donateur> donateurstmp = p.getDonateurs ().stream ().filter (donateur -> !(donateur.isPaye () && donateur.isAnnule ())).collect(Collectors.toList());
			donateurs.addAll (donateurstmp);
		}
		return donateurs;
	}

	public List<Projet> toList () {
		return new LinkedList <> (this.projets);
	}

	public void addProjets (List<Projet> projets) {
		for (Projet p : projets)
			this.addProjet (p);
	}

	public boolean addProjet (Projet projet) {
		Projet p = getProjet (projet.getProjet ());
		if (p == null) { // Si le projet n'existe pas
			projet.triDonateursNomPrenom ();
			this.projets.add (projet);
			return true;
		}
		else { // Si le projet existe déjà, on fusionne les nouveaux et anciens donateurs
			p.addDonateurs (projet.getDonateurs ());
			return false;
		}
	}

	public Projet getProjet (long id) {
		for (Iterator <Projet> it = this.projets.iterator() ; it.hasNext(); ) {
			Projet p = it.next();
			if (p.getId () == id)
				return p;
		}
			return null;
	}

	public Projet getProjet (String projetName) {
		for (Iterator <Projet> it = this.projets.iterator() ; it.hasNext(); ) {
			Projet p = it.next();
			if (p.getProjet ().equalsIgnoreCase (projetName))
				return p;
		}
		return null;
	}

	public List<Donateur> allDonateurs () {
		List<Donateur> donateurs = new LinkedList <> ();
		for (Projet p : this.projets)
			donateurs.addAll (p.getDonateurs ());
		return donateurs;
	}

	public Set <Projet> getProjets () {
		return projets;
	}

	public void setProjets (Set <Projet> projets) {
		this.projets = projets;
	}

	public int getSize () {
		return this.projets.size ();
	}

	public String toString (boolean avecDonateurs) {
		StringBuilder sb = new StringBuilder ();
		for (Projet p : this.projets)
			sb.append (p.toString (avecDonateurs));
		return sb.toString ();
	}
}