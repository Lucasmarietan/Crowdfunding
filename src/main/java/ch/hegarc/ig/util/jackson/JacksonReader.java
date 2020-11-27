package ch.hegarc.ig.util.jackson;

//  TODO - Meilleure méthode ? pour : Faire pour stocker les données de manière persistante et globale au projet

//  Pour l'instant, il sait juste lire le json (et afficher les projets avec ses donateurs)

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonReader {
	private static final Logger logger = Logger.getLogger(JacksonReader.class.getName());

	public static List<Projet> run(String filename) {
		List<Projet> projets = null;
		try {
//			ObjectMapper - Ignorer les propriétés inconnues
			ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			projets = om.readValue(new File(filename), new TypeReference<List<Projet>>(){});
//			for (Projet p : projets)
//				System.out.println (p.toString());
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		return projets;
	}
}