package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToPDF {
	private static final Logger logger = Logger.getLogger (ExportToPDF.class.getName ());

	/**
	 * @param projet Pour exporter informations d'un projet dans un PDF
	 */
	public static void run (Projet projet) {
		try {
			PDDocument doc = new PDDocument ();
			PDPage page = new PDPage ();
			doc.addPage (page);
			PDPageContentStream cos = new PDPageContentStream (doc, page);
			cos.setFont (PDType1Font.HELVETICA_BOLD, 16); cos.beginText (); cos.newLineAtOffset (20, 750); // On place la 1ère ligne
			cos.setLeading (17.5f); // Choix d'un seul paragraphe pour la 1ère page
			cos.showText ("Bilan du projet : " + projet.getProjet ());
			cos.setFont (PDType1Font.HELVETICA_BOLD, 12); cos.newLine (); cos.newLine ();
			cos.showText ("  Argent total : " + (projet.argentTotal ()) + " CHF"); cos.newLine (); // Choix de généraliser aux CHF
			cos.showText ("  Commission : " + (projet.commission ()) + " CHF"); cos.newLine (); cos.newLine (); // Légère indentation "en dur"

			cos.setLeading (14.5f);
			cos.showText ("  Les 5 plus gros donateurs :");
			cos.setFont (PDType1Font.HELVETICA, 10); cos.newLine ();
			for (Donateur d : projet.plusGrosDonateur (5)) {
				cos.showText ("    " + d.toString ()); cos.newLine ();
			}
			cos.setFont (PDType1Font.HELVETICA_BOLD,12); cos.newLine (); cos.newLine ();
			cos.showText ("  Les donateurs qui n'ont pas payé (ni annulé) :");
			cos.setFont (PDType1Font.HELVETICA, 10);	cos.newLine ();
			for (Donateur d : projet.pasEncorePaye ()) {
				cos.showText ("    " + d.toString ()); cos.newLine ();
			}
			cos.endText ();
			cos.close ();

			PDPage pageMail = new PDPage ();
			doc.addPage (pageMail);
			PDPageContentStream cosMail = new PDPageContentStream (doc, pageMail);
			cosMail.setFont (PDType1Font.HELVETICA_BOLD, 12); cosMail.beginText (); cosMail.newLineAtOffset (20, 750); cosMail.setLeading (14.5f);
			cosMail.showText ("Tous les mails de ce projet :");
			cosMail.setFont (PDType1Font.HELVETICA, 10); cosMail.newLine ();
			String[] donateurs = projet.tousEmail ().split (";");
			for (int i = 0; i < donateurs.length; i++) {
				if (i % 4 == 0) // Choix de passer à la ligne tous les 4 mails pour une meilleure lisibilité
					cosMail.newLine ();
				cosMail.showText (donateurs[i] + ";");
			}
			cosMail.endText (); cosMail.close ();

			doc.save ("Bilan du projet " + projet.getProjet () + ".pdf");
			logger.log (Level.INFO, "Fichier '" + "Bilan du projet " + projet.getProjet () + ".pdf" + "' produit sans erreur !");
			doc.close ();
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}
}