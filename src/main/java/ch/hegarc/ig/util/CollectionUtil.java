package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//  Cette classe a été créée pour les méthodes qui ne s'appliquent pas à tous les projets

public class CollectionUtil {
	/**
	 * (Méthode pas explicitement demandée mais nécessaire pour le fichier Excel)
	 *
	 * @param projet
	 * @return le nombre de dons pas annulé (payés ou non payés)
	 */
	public static int nombreDonsPasAnnule (Projet projet) {
		return (int) projet.getDonateurs ().stream ().filter (donateur -> ! donateur.isAnnule ()).count ();
	}

	/**
	 * @param projet
	 * @param nb
	 * @return les plus gros donateurs du projet (dons exécutés ou pas, annulés ou pas)
	 */
	public static List <Donateur> plusGrosDonateur (Projet projet, int nb) { // Libre à l'utilisateur de choisir un autre nombre que 5
		return projet.getDonateurs ().stream ().sorted (Comparator.comparing (Donateur::getSomme).reversed ()).limit (nb).collect (Collectors.toList ());
	}

	/**
	 * @param projet
	 * @return les dons déjà payés
	 */
	public static long argentDejaPaye (Projet projet) {
		return projet.getDonateurs ().stream ().filter (Donateur::isPaye).mapToLong (Donateur::getSomme).sum ();
	}

	/**
	 * @param projet
	 * @return les dons pas payé et pas annulé
	 */
	public static long argentRestantAPaye (Projet projet) {
		return projet.getDonateurs ().stream ().filter (donateur -> ! donateur.isPaye () && ! donateur.isAnnule ()).mapToLong (Donateur::getSomme).sum ();
	}

	/**
	 * @param projet
	 * @return les dons pas encore annulé (déja payé ou pas)
	 */
	public static long argentTotal (Projet projet) {
		return projet.getDonateurs ().stream ().filter (donateur -> ! donateur.isAnnule ()).mapToLong (Donateur::getSomme).sum ();
	}

	public static String tousEmail (Projet projet) {
		StringBuilder sb = new StringBuilder ();
		List <Donateur> donateurs = projet.getDonateurs ();
		for (Donateur d : donateurs) {
			if (d.getEmail () != null) // Pour obtenir une liste sans éléments vide
				sb.append (d.getEmail ()).append (";");
		}
		return sb.toString ();
	}

	/**
	 * @param projet
	 * @return la médiane de TOUS les dons du projet (exécutés ou pas, annulé ou pas)
	 */
	public static long medianeDons (Projet projet) { // Pas peu fier de moi sur celle là (:
		return projet.getDonateurs ().stream ().sorted (Comparator.comparing (Donateur::getSomme).reversed ()).skip (projet.getDonateurs ().size () / 2).limit (1).collect (Collectors.toList ()).get (0).getSomme ();
	}

	/**
	 * @param projet
	 * @return la moyenne de TOUS les dons du projet (exécutés ou pas, annulés ou pas)
	 */
	public static long moyenneDons (Projet projet) {
		return (long) projet.getDonateurs ().stream ().mapToLong (Donateur::getSomme).average ().orElse (- 100);
	}

	/**
	 * @param projet
	 * @return le calcul de la commission sur TOUS les dons
	 */
	public static long commission (Projet projet) {
		return (long) (CollectionUtil.argentTotal (projet) * 0.05);
	}
}