package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToExcel {
	private static final Logger logger = Logger.getLogger (ExportToExcel.class.getName ());

	/**
	 * @param projets  Pour exporter les stats de tous les projets
	 */
	public static void run (List <Projet> projets) {
		XSSFWorkbook wb = miseEnPage ();
		XSSFSheet sheet = wb.getSheetAt (0);
		wb.setSheetName (wb.getSheetIndex (sheet), "Projets");
		for (int i = 0 ; i < projets.size () ; i++)
			wb = addProjet (wb, projets.get (i), i + 1); // On appelle cette fonction pour chaque projet. Après un projet, on décale d'une colonne vers la droite

		try (OutputStream fileOut = new FileOutputStream ("stats.xlsx")) {
			wb.write (fileOut);
			logger.log (Level.INFO, "Fichier 'stats.xlsx' créé avec succès !");
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	/**
	 * @param projet   Pour exporter les stats d'un seul projet
	 */
	public static void run (Projet projet) {
		XSSFWorkbook wb = miseEnPage ();
		XSSFSheet sheet = wb.getSheetAt (0);
		wb.setSheetName (wb.getSheetIndex (sheet), projet.getProjet ());

		wb = addProjet (wb, projet, 1);

		try (OutputStream fileOut = new FileOutputStream ("stats.xlsx")) {
			wb.write (fileOut);
			logger.log (Level.INFO, "Fichier 'stats.xlsx' créé avec succès !");
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	/**
	 * @param wb         Pour travailler sur un fichier dans toute la classe
	 * @param projet
	 * @param numColonne Pour décaler de colonne à chaque projet
	 * @return Pour travailler sur un seul fichier dans toute la classe
	 */
	private static XSSFWorkbook addProjet (XSSFWorkbook wb, Projet projet, int numColonne) {
		XSSFSheet sheet = wb.getSheetAt (0);
		XSSFRow row = sheet.getRow (0);
		XSSFCell cell = row.createCell (numColonne);
		cell.setCellValue (projet.getProjet ());
		row = sheet.getRow (1);
		cell = row.createCell (numColonne);
		cell.setCellValue (ProjetUtils.argentDejaPaye (projet));
		row = sheet.getRow (2);
		cell = row.createCell (numColonne);
		cell.setCellValue (ProjetUtils.argentRestantAPaye (projet));
		row = sheet.getRow (3);
		cell = row.createCell (numColonne);
		cell.setCellValue (ProjetUtils.argentTotal (projet));
//		Pour mettre la cellule en gras (Ligne TOTAL)
		XSSFCellStyle styleBold = wb.createCellStyle ();
		XSSFFont font = wb.createFont ();
		font.setBold (true);
		styleBold.setFont (font);
		cell.setCellStyle (styleBold);

		row = sheet.getRow (5);
		cell = row.createCell (numColonne);
		cell.setCellValue (ProjetUtils.nombreDonsPasAnnule (projet));
		row = sheet.getRow (6);
		cell = row.createCell (numColonne);
		cell.setCellValue (ProjetUtils.moyenneDons (projet));

		sheet.autoSizeColumn (numColonne); // Pour ajuster la largeur de la colonne au contenu

		return wb;
	}

	/**
	 * @return Un fichier avec le intitulé déjà fait
	 */
	private static XSSFWorkbook miseEnPage () {
		XSSFWorkbook wb = new XSSFWorkbook ();
		XSSFSheet sheet = wb.createSheet ();

		XSSFRow row1 = sheet.createRow (0);
		XSSFCell cell1 = row1.createCell (0);
		cell1.setCellValue ("Projet (nom)");

		XSSFRow row2 = sheet.createRow (1);
		XSSFCell cell2 = row2.createCell (0);
		cell2.setCellValue ("Total payé");

		XSSFRow row3 = sheet.createRow (2);
		XSSFCell cell3 = row3.createCell (0);
		cell3.setCellValue ("Total à recevoir");

		XSSFRow row4 = sheet.createRow (3);
		XSSFCell cell4 = row4.createCell (0);
		cell4.setCellValue ("Total");
//		Pour mettre la cellule en gras (Ligne TOTAL)
		XSSFCellStyle styleBold = wb.createCellStyle ();
		XSSFFont font = wb.createFont ();
		font.setBold (true);
		styleBold.setFont (font);
		cell4.setCellStyle (styleBold);

		XSSFRow row6 = sheet.createRow (5);
		XSSFCell cell6 = row6.createCell (0);
		cell6.setCellValue ("Nombre de dons (non-annulés)");

		XSSFRow row7 = sheet.createRow (6);
		XSSFCell cell7 = row7.createCell (0);
		cell7.setCellValue ("Moyenne des dons");

		sheet.autoSizeColumn (0);
		return wb;
	}
}