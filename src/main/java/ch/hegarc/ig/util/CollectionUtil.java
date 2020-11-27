package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtil {
	public CollectionUtil () {

	}

//	Fonctionne ! A voir encore où on l'appelle
	public static List <Donateur> plusGrosDonateur (Projet projet, int nb) {
		Stream <Donateur> projetStream = projet.getDonateurs ().stream ().sorted(Comparator.comparing (Donateur::getSomme).reversed ());
		return projetStream.limit (nb).collect(Collectors.toList());
	}

	public static List<Donateur> pasEncorePaye (Projet projet) {
		return null;
	}

//	public static long argentPaye (Projet projet) {
//		Stream<Donateur> donateurStream = projet.getDonateurs ().stream ().filter (donateur -> donateur.get);
//		return donateurStream.mapToLong (donateur -> donateur.getSomme ()).sum ();
//	}

//	Fonctionne ! A voir encore où on l'appelle
	public static String tousEmail (Projet projet) {
		StringBuilder sb = new StringBuilder ();
		List<Donateur> donateurs = projet.getDonateurs ();
		for (Donateur d : donateurs) {
			if (d.getEmail () != null)
				sb.append (d.getEmail ()).append (";");
		}
		return sb.toString ();
	}

//	public static long commission (Projet projet) {
//
//	}
}