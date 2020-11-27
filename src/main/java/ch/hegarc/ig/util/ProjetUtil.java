package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;

import java.util.*;

public class ProjetUtil {
	private Set<Projet> projets;

	public ProjetUtil () {
		this.projets = new HashSet <> ();
	}

	public ProjetUtil (Set <Projet> projets) {
		this.projets = projets;
	}

	public void addProjet (List<Projet> projets) {
		for (Projet p : projets)
			this.addProjet (p);
	}

	public void addProjet (Projet projet) {
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
}