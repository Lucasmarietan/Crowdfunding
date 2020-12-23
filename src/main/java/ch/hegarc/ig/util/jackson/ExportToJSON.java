package ch.hegarc.ig.util.jackson;

import ch.hegarc.ig.business.Projet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToJSON {
	private static final Logger logger = Logger.getLogger (ExportToJSON.class.getName ());

	/**
	 * @param projets  Pour exporter tous les projets
	 * @param fileName Dans un .json
	 */
	public static void run (List <Projet> projets, String fileName) {
		try {
			ObjectMapper om = new ObjectMapper ();
			om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projets);

			logger.log (Level.INFO, "Fichier '" + fileName + "' produit sans erreur !");
		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
	}

	/**
	 * @param projet   Pour exporter un seul projet
	 * @param fileName Dans un .json
	 */
	public static void run (Projet projet, String fileName) {
		try {
			ObjectMapper om = new ObjectMapper ();
			om.writerWithDefaultPrettyPrinter ().writeValue (new File (fileName), projet);

			logger.log (Level.INFO, "Fichier '" + fileName + "' produit sans erreur ! ");
		} catch (IOException ex) {
			logger.log (Level.SEVERE, null, ex);
		}
	}
}