package ch.hegarc.ig.util.jackson;

//Pour l'instant le JacksonWriter permet d'exporter un projet (généré avec newPopProjet...) avec ses donateurs dans un json

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonWriter {
	private static final Logger logger = Logger.getLogger (JacksonWriter.class.getName ());

	public static void run (Projet projet, String fileName) {
		try {
			ObjectMapper om = new ObjectMapper ();

//			Ignorer les champs vide - TODO Copier de la série 5. On garde ?
//			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//			Ecriture avec pretty print
			om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projet);

			logger.log (Level.INFO, "Fichier " + fileName + " créé");

		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
	}
}