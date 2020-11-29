package ch.hegarc.ig.util;

//	TODO - Checker comment on appelera ces méthodes dans la console

//  TODO - Méthodes à implémenter encore :
//   Pour une liste de noms séparés par des virgules et reçu en argument, calculer le total des dons pour ces personnes, quelque soit le projet

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtil {
//	Fonctionne ! A voir encore où on l'appelle
	public static List <Donateur> plusGrosDonateur (Projet projet, int nb) {
		Stream <Donateur> projetStream = projet.getDonateurs ().stream ().sorted(Comparator.comparing (Donateur::getSomme).reversed ());
		return projetStream.limit (nb).collect(Collectors.toList());
	}

//	Fonctionne !
	public static List<Donateur> pasEncorePaye (Projet projet) {
		return projet.getDonateurs ().stream ().filter (donateur -> donateur.isPaye () == false && donateur.isAnnule () == false).collect(Collectors.toList());
	}

//	Fonctionne !
	public static long argentDejaPaye (Projet projet) {
		Stream<Donateur> donateurStream = projet.getDonateurs ().stream ().filter (donateur -> donateur.isPaye ());
		return donateurStream.mapToLong (donateur -> donateur.getSomme ()).sum ();
	}

//	Fonctionne !
	public static long argenrRestantAPaye (Projet projet) {
		Stream<Donateur> donateurStream = projet.getDonateurs ().stream ().filter (donateur -> donateur.isPaye () == false);
		return donateurStream.mapToLong (donateur -> donateur.getSomme ()).sum ();
	}

//	Fonctionne !
	public static long argentTotal (Projet projet) {
		return argentDejaPaye (projet) + argenrRestantAPaye (projet);
	}

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

//	Fonctionne ! (Sur TOUS les dons)
	public static long medianeDons (Projet projet) { // Pas peu fier de moi sur celle là (:
		return projet.getDonateurs ().stream ().sorted(Comparator.comparing (Donateur::getSomme).reversed ()).skip(projet.getDonateurs ().size () / 2).limit (1).collect(Collectors.toList()).get (0).getSomme ();
	}

//	Fonctionne ! (Sur TOUS les dons)
	public static long moyenneDons (Projet projet) {
		return (long) projet.getDonateurs ().stream ().mapToLong (donateur -> donateur.getSomme ()).average ().orElse (-1);
	}

//	Fonctionne ! Dans sa version la plus simple bien sûr... Pas de gestion des erreurs
//	Ne gère pas un donateur sur plusieurs projet...
	public static long totalDonsDonateurs (List<Projet> tousProjets, String donateurs) {
		double somme;
		Map <Donateur, Double> donateursMap = new HashMap <> ();
		List<String> dona = Arrays.asList (donateurs.split (","));
		for (String s : dona) {
			for (Projet p : tousProjets) {
				for (Donateur d : p.getDonateurs ()) {
					if (s.equalsIgnoreCase (d.getNom ())) {
						System.out.println ("A");
						if (!donateursMap.isEmpty ()) {
							for (Map.Entry<Donateur, Double> entry : donateursMap.entrySet()) {
								System.out.println ("B");
								if (s.equalsIgnoreCase (entry.getKey ().getNom ())) {
									System.out.println ("C");
									somme = donateursMap.get (d); // TODO - Pouvoir récupérer la valeur déjà dans la Map pour ensuite y ajouter la valeur du don actuel....
									System.out.println ("somme presente : " + somme);
									System.out.println ("DON : " + d.getSomme ());
									donateursMap.put (d, somme + d.getSomme ());
								} else {
									System.out.println ("D");
									donateursMap.put (d, (double) d.getSomme ());
								}
							}
						}
						else {
							System.out.println ("Empty");
							System.out.println ("Don : " + d.getSomme ());
							donateursMap.put (d, (double) d.getSomme ());
						}
					}
				}
			}
		}
		somme = 0;
		for (Map.Entry<Donateur, Double> entry : donateursMap.entrySet()) {
			somme = somme + entry.getValue ();
		}
		return (long) somme;
//		TODO - Implementer cette fonction !
//		throw new NullPointerException ("Pas encore implémentée...");
	}

//	Fonctionne ! (Sur TOUS les dons)
	public static long commission (Projet projet) {
		return (long) (CollectionUtil.argentTotal (projet) * 0.05);
	}
}