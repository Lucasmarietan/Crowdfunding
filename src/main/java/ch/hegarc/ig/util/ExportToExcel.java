package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class ExportToExcel {
	private static final Logger logger = Logger.getLogger (ExportToExcel.class.getName ());

//	public static void run () {
//
//	}

	public static void run (Projet projet) {
		XSSFWorkbook wb = miseEnPage ();
//		XSSFWorkbook wb = new XSSFWorkbook ();
//		XSSFSheet sheet = wb.createSheet ("Stats_" + projet.getProjet ()); // Nom de la feuille
//		XSSFRow row = sheet.createRow (rowNb);
//		XSSFCell cell = row.createCell (columnNb);
//		cell.setCellValue ("Prénom");
//		cell.setCellFormula ("AVERAGEA(A2:C2)");
		try (OutputStream fileOut = new FileOutputStream ("file.xlsx")) {
			wb.write (fileOut);
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

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
		XSSFCellStyle styleBold = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setBold (true);
		styleBold.setFont (font);
		row4.setRowStyle (styleBold);

		XSSFRow row5 = sheet.createRow (4);

		XSSFRow row6 = sheet.createRow (5);
		XSSFCell cell6 = row6.createCell (0);
		cell6.setCellValue ("Nombre de dons (non-annulés)");

		XSSFRow row7 = sheet.createRow (6);
		XSSFCell cell7 = row7.createCell (0);
		cell7.setCellValue ("Nombre de dons (non-annulés)");

		return wb;
	}
}