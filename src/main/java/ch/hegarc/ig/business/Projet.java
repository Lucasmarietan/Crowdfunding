package ch.hegarc.ig.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Projet {

	private long id;
	private String projet;
	private List<Donateur> donateurs = new ArrayList<Donateur>();

	public Projet() {
	}

	/**
	 * @param donateurs
	 * @param name
	 * @param id
	 */
	public Projet(long id, String name, List<Donateur> donateurs) {
		super();
		this.id = id;
		this.projet = name;
		this.donateurs = donateurs;
	}

	public void triDonateurs () {
		Stream<Donateur> sD = this.donateurs.stream ().sorted (Comparator.comparing (Donateur::getNom)).sorted (Comparator.comparing (Donateur::getPrenom));
		this.donateurs = sD.collect(Collectors.toList());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjet () {
		return projet;
	}

	public void setProjet (String projet) {
		this.projet = projet;
	}

	public List<Donateur> getDonateurs() {
		return donateurs;
	}

	public void setDonateurs(List<Donateur> donateurs) {
		this.donateurs = donateurs;
	}

	public String toString (boolean avecDonateurs) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("Nom projet : ").append (this.projet).append (" (id : ").append (this.id).append (")\n");
		if (avecDonateurs) {
			sb.append ("Les donateurs de ce projet : \n");
			for (Donateur d : this.donateurs)
				sb.append (d.toString ()).append ("    Montant : ").append (d.getSomme ()).append (" ").append (d.getMonnaie ()).append ("\n");
		}
		return sb.toString ();
	}

	public static List<Projet> newPopProjets () {
		List<Projet> projets = new LinkedList <> ();
		List<Donateur> donateurs = Donateur.newPopDonateurs ();
		List<Donateur> donateurs1 = Donateur.newPopDonateurs ();
		donateurs1.remove (1);
//		System.out.println (donateurs1);
		donateurs.remove (0);
//		System.out.println (donateurs);
		Projet p1 = new Projet (90, "Haute Ecole de ConGestion", donateurs);
//		System.out.println (p1.toString (true));
		Projet p2 = new Projet (91, "Voiture", donateurs1);
//		System.out.println (p2.toString (true));
		projets.add (p1); projets.add (p2);
		return projets;
	}
}