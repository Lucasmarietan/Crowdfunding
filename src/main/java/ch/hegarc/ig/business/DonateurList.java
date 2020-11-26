package ch.hegarc.ig.business;

import java.util.ArrayList;
import java.util.List;

public class DonateurList {
//	On devra la supprimer cette classe

	private List<Donateur> donateurs = new ArrayList<>();

//    @JsonProperty("group")
	/**
	 * Les deux attributs suivant sont ceux qui se trouvent au début de "donations.json
	 */
	private Integer ID;
	private String projet;

	public DonateurList() {
	}

	public DonateurList(Integer ID, String projet) {
		this.ID = ID;
		this.projet = projet;
	}

	public List<Donateur> getDonateurs() {
		return donateurs;
	}

	public Integer getID() {
		return ID;
	}

	public String getProjet(){
		return projet;
	}


	public void setID(Integer ID) {
		this.ID = ID;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}

	public static DonateurList newPopulatedStudents() {
		DonateurList donateurList = new DonateurList(2, "Projet1");
		// c'est vraiment long pour mettre des donateurs alors j'en mets qu'un
		donateurList.getDonateurs().add(new Donateur(1L, "lucas", "marietan", "marietan.lucas@gmail.com", "francais", "rue de la paix",
				"Lausanne", "CHF", 50L, true, true, "janvier", "janvier"));

		return donateurList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("-- My donateurs' list --\n");

		for (Donateur d : getDonateurs()) {
			sb.append(d);
		}
		return sb.toString();
	}

}
