package ch.hegarc.ig.util;

// Classe propre.
// TODO - Gérer l'unicité des projets (par nom) et l'unicité des donateurs (par nom et prénom) dans le projet

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

//	Fonctionne !
	public List<Donateur> pasEncorePaye () {
		List<Donateur> donateurs = new LinkedList <> ();
		for (Projet p : this.projets) {
			List<Donateur> donateurstmp = p.getDonateurs ().stream ().filter (donateur -> !(donateur.isPaye () && donateur.isAnnule ())).collect(Collectors.toList());
			donateurs.addAll (donateurstmp);
		}
		return donateurs;
	}

	public Projet contient (String nomProjet) {
		for (Projet p : this.projets) {
			if (p.getProjet ().equalsIgnoreCase (nomProjet))
				return p;
		}
		return null; // Il faut faire le test dans la console si besoin
	}

	public List<Projet> toList () {
		return new LinkedList <> (this.projets);
	}

	public void addProjets (List<Projet> projets) {
		for (Projet p : projets)
			this.addProjet (p);
	}

	public void addProjet (Projet projet) {
		projet.triDonateursNomPrenom ();
		this.projets.add (projet);
	}

	public Projet getProjet (long id) {
		for (Iterator <Projet> it = this.projets.iterator() ; it.hasNext(); ) {
			Projet p = it.next();
			if (p.getId () == id) {
				return p;
			}
		}
			return null;
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