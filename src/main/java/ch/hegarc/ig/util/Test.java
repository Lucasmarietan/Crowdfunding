package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static void main (String[] args) {
//		On a fait le choix d'un HashSet pour stocker les projets dans le programme
/*		Set <String> projets = new TreeSet <> ();
		projets.add ("3");
		projets.add ("2b");
		projets.add ("2a");
		projets.add ("1a");
		for (String s : projets)
			System.out.println (s);
		System.out.println ("----");
		Stream<String> ss = projets.stream ().sorted();
		projets = ss.collect(Collectors.toSet());
		for (String s : projets)
			System.out.println (s);
*/
		List<Projet> projetList = new LinkedList <> (Projet.newPopProjets ());
		for (Projet pj : projetList)
			System.out.println (pj.toString (false));
		System.out.println ("-SET-");
		Set <Projet> projets = new TreeSet <> (Projet.newPopProjets ());
		for (Projet pj : projets)
			System.out.println (pj.toString (false));

//		Stream pour classer par nom de projet (attribut dans Projet : "projet")
		Stream <Projet> pS = projets.stream ().sorted(Comparator.comparing (Projet::getProjet));
//		On convertit le Stream en Set
		projets = pS.collect (Collectors.toSet ());
		System.out.println ("SET trié ?");
		for (Projet pj : projets)
			System.out.println (pj.toString (false));


//		for (Projet pj : projets)
//			System.out.println (pj.toString (false));
//		Avec newPopProjet(), on ajoute deux projet :
//	    	1er él : Voiture. 2ème él : Ecole. (sans ordre abc)

//		Stream pour classer par nom de projet (attribut dans Projet : "projet")
//		Stream <Projet> pS = projets.stream ().sorted(Comparator.comparing (Projet::getProjet));
//		On convertit le Stream en Set
//		projets = pS.collect (Collectors.toSet ());
//		System.out.println ("SET trié ?"); // Spoiler : non.
//		for (Projet pj : projets)
//			System.out.println (pj.toString (false));
//		Output : Voiture, Ecole => FAUX ! (Pas d'ordre abc...)
	}
}