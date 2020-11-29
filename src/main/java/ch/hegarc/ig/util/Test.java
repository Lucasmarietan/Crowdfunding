package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static void main (String[] args) {
//		On a fait le choix d'un HashSet pour stocker les projets dans le programme
		Set <Projet> projets = new LinkedHashSet <> (Projet.newPopProjets ());
//		Avec newPopProjet(), on ajoute deux projet :
//	    	1er él : Voiture. 2ème él : Ecole. (sans ordre abc)

//		Stream pour classer par nom de projet (attribut dans Projet : "projet")
		Stream <Projet> pS = projets.stream ().sorted(Comparator.comparing (Projet::getProjet));
//		On convertit le Stream en Set
		projets = pS.collect (Collectors.toSet ());
		System.out.println ("SET trié ?"); // Spoiler : non.
		for (Projet pj : projets)
			System.out.println (pj.toString (false));
//		Output : Voiture, Ecole => FAUX ! (Pas d'ordre abc...)
	}
}