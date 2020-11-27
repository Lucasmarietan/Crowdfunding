package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjetUtil {
	private Set<Projet> projets;

	public ProjetUtil () {
		this.projets = new HashSet <> ();
	}

	public ProjetUtil (Set <Projet> projets) {
		this.projets = projets;
	}

//	TODO - Le stream fonctionne mais quand on remet dans this.projets, ça revient à la version originale...
	public void triAlphabetique () {
		List<Projet> p = new ArrayList <> (this.projets);
		System.out.println ("--- Liste avec projets ---");
		for (Projet pj : p)
			System.out.println (pj.toString (false));
		Stream <Projet> pS = p.stream ().sorted(Comparator.comparing (Projet::getProjet));
		p = pS.collect(Collectors.toList());
		System.out.println ("--- Liste après Stream ---");
		for (Projet pj : p)
			System.out.println (pj.toString (false));
		this.projets = new HashSet <> (p);
		System.out.println ("--- HashSet avec nouvelle liste ---");
		for (Projet pj : this.projets)
			System.out.println (pj.toString (false));
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