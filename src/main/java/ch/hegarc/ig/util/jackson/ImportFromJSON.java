package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportFromJSON {
	private static final Logger logger = Logger.getLogger (ImportFromJSON.class.getName ());

	public static List <Projet> run (String filename) {
		List <Projet> projets = null;
		try {
			ObjectMapper om = new ObjectMapper ().configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			projets = om.readValue (new File (filename), new TypeReference <List <Projet>> () {
			});

			logger.log (Level.INFO, "Fichier '" + filename + "' importé dans le programme avec succès ! ");
		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
		return projets;
	}
}