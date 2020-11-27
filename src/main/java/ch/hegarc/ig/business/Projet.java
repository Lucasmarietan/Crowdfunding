package ch.hegarc.ig.business;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append ("Nom projet : ").append (this.projet).append (" (id : ").append (this.id).append (")\n");
		sb.append ("Les donateurs de ce projet : \n");
		for (Donateur d : this.donateurs)
			sb.append (d.toString ()).append ("    Montant : ").append (d.getSomme ()).append (" ").append (d.getMonnaie ()).append ("\n");
		return sb.toString ();
	}

	public static List<Projet> newPopProjets () {
		List<Projet> projets = new LinkedList <> ();
		List<Donateur> donateurs = Donateur.newPopDonateurs ();
		Projet p1 = new Projet (90, "Haute Ecole de ConGestion", donateurs);
		donateurs.remove (2); donateurs.get (0).setSomme (11);
		Projet p2 = new Projet (91, "Voiture", donateurs);
		projets.add (p1); projets.add (p2);
		return projets;
	}
}