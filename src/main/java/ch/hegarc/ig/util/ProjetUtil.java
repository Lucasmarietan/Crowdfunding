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

	public Projet contientProjet (String nomProjet) {
		for (Projet p : this.projets) {
			if (p.getProjet ().equalsIgnoreCase (nomProjet))
				return p;
		}
		return null;
	}

	public boolean contient (String nomProjet) {
		boolean existe = false;
		for (Projet p : this.projets) {
			if (p.getProjet ().equalsIgnoreCase (nomProjet))
				existe = true;
		}
		return existe;
	}

	public List<Projet> toList () {
		return new LinkedList <> (this.projets);
	}

	public void addProjets (List<Projet> projets) {
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

	public int getSize () {
		return this.projets.size ();
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		for (Projet p : this.projets)
			sb.append (p.toString ());
		return sb.toString ();
	}
}