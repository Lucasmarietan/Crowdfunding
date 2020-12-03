package ch.hegarc.ig.util.jackson;

// Classe prête et qui fonctionne !

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonWriter {
	private static final Logger logger = Logger.getLogger (JacksonWriter.class.getName ());

//	Pour exporter tous les projets dans un json
	public static void run (List<Projet> projets, String fileName) {
		try {
			ObjectMapper om = new ObjectMapper ();

//			Ignorer les champs vide - TODO Copier de la série 5. On garde ?
//			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//			Ecriture avec pretty print
			om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projets);

			logger.log (Level.INFO, "Fichier '" + fileName + "' crée avec succès !");

		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
	}

//	Pour exporter un seul projet dans un JSON
	public static void run (Projet projet, String fileName) {
		try {
			ObjectMapper om = new ObjectMapper ();

//			Ignorer les champs vide - TODO Copier de la série 5. On garde ?
//			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//			Ecriture avec pretty print
			om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projet);

			logger.log (Level.INFO, "Fichier '" + fileName + "' créé avec succès ! ");
		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
	}
}