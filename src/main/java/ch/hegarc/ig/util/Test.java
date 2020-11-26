package ch.hegarc.ig.util;

import java.util.StringTokenizer;

public class Test {
	public static void main(String[] args) {
		String fileName = "donations.json";
		if (fileName.contains (".")) {
			String[] fff = fileName.split ("\\.");
			System.out.println (fff);
			String nomFichier = fff[0];
			String extension = fff[1];
			System.out.println (nomFichier);
			System.out.println (extension);
		}
		else {
			throw new IllegalArgumentException("String " + fileName + " does not contain .");
		}
	}
}