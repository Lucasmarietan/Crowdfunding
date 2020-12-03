package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjetUtil {
	private Set<Projet> projets;

	public ProjetUtil () {
		this.projets = new TreeSet <> ();
	}

	public ProjetUtil (Set <Projet> projets) {
		this.projets = projets;
	}

//	Fonctionne ! TODO - On garde bien cette méthode ? (Dans cette classe)
	public List<Donateur> pasEncorePaye () {
		List<Donateur> donateurs = new LinkedList <> ();
		for (Projet p : this.projets) {
			List<Donateur> donateurstmp = p.getDonateurs ().stream ().filter (donateur -> donateur.isPaye () == false && donateur.isAnnule () == false).collect(Collectors.toList());
			donateurs.addAll (donateurstmp);
		}
		return donateurs;
	}

//	TODO - Les 2 méthodes ci-après peuvent sûrement être combinée
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
		for (Projet p : projets) {
			this.addProjet (p);
		}
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

	public String toString (boolean avecDonateurs) {
		StringBuilder sb = new StringBuilder ();
		for (Projet p : this.projets)
			sb.append (p.toString (avecDonateurs));
		return sb.toString ();
	}
}