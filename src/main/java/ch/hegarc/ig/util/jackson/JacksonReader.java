package ch.hegarc.ig.util.jackson;

/** Classe capable de lire le fichier donations.json*/

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

	public static void run(String filename) {
		try {
//			ObjectMapper - Ignorer les propriétés inconnues
			ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			List<Projet> projets = om.readValue(new File(filename), new TypeReference<List<Projet>>(){});
			for (Projet p : projets)
				System.out.println (p.toString());
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}
}