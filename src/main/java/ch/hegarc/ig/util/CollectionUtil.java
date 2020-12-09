package ch.hegarc.ig.util;

//	TODO - Checker comment on appelera ces méthodes dans la console

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.util.jackson.JacksonWriter;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//  Cette classe a été créée pour les méthodes qui ne s'appliquent pas à tous les projets

public class CollectionUtil {
//	Fonctionne !
	public static List <Donateur> plusGrosDonateur (Projet projet, int nb) { // Libre à l'utilisateur de choisir un autre nombre que 5
		return projet.getDonateurs ().stream ().sorted(Comparator.comparing (Donateur::getSomme).reversed ()).limit (nb).collect(Collectors.toList());
	}

//	Fonctionne !
	public static long argentDejaPaye (Projet projet) {
		return projet.getDonateurs ().stream ().filter (Donateur::isPaye).mapToLong (Donateur::getSomme).sum ();
	}

//	Fonctionne !
	public static long argentRestantAPaye (Projet projet) {
		return projet.getDonateurs ().stream ().filter (Donateur::isPaye).mapToLong (Donateur::getSomme).sum ();
	}

//	Fonctionne !
	public static long argentTotal (Projet projet) {
		return projet.getDonateurs ().stream ().mapToLong (Donateur::getSomme).sum ();
	}

//	Fonctionne ! A voir encore où on l'appelle
	public static String tousEmail (Projet projet) {
		StringBuilder sb = new StringBuilder ();
		List<Donateur> donateurs = projet.getDonateurs ();
		for (Donateur d : donateurs) {
			if (d.getEmail () != null) // Pour obtenir une liste sans éléments vide
				sb.append (d.getEmail ()).append (";");
		}
		return sb.toString ();
	}

//	Fonctionne ! (Sur TOUS les dons du projet)
	public static long medianeDons (Projet projet) { // Pas peu fier de moi sur celle là (:
		return projet.getDonateurs ().stream ().sorted(Comparator.comparing (Donateur::getSomme).reversed ()).skip(projet.getDonateurs ().size () / 2).limit (1).collect(Collectors.toList()).get (0).getSomme ();
	}

//	Fonctionne ! (Sur TOUS les dons du projet)
	public static long moyenneDons (Projet projet) {
		return (long) projet.getDonateurs ().stream ().mapToLong (Donateur::getSomme).average ().orElse (-100);
	}

//	Fonctionne ! (Sur TOUS les dons du projet)
	public static long commission (Projet projet) {
		return (long) (CollectionUtil.argentTotal (projet) * 0.05);
	}
}