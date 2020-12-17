package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.MainUnmarshalling;
import ch.hegarc.ig.util.jackson.JacksonReader;
import ch.hegarc.ig.util.jackson.JacksonWriter;
import org.apache.commons.cli.*;

import java.util.Scanner;

public class Console {
	final private String CMD_IMPORT = "import";
	final private String CMD_EXPORT = "export";
	final private String CMD_EXIT = "exit";
	final private String CMD_STATS = "stats";
	//	Nouvelle commande
	final private String CMD_ADD_DONATEUR = "don";
	final private String CMD_REMOVE_DONATEUR = "del";
	final private String CMD_HELP = "help";
	final private Option OPT_FICHIER = new Option ("f", "fichier", true, "nom du fichier");
	final private Option OPT_PROJET = new Option ("p", "projet", true, "nom du projet");
	//	Nouvelles options
	final private Option OPT_DON_NOM = new Option ("n", "donateurNom", true, "nom du donateur");
	final private Option OPT_DON_PRENOM = new Option ("r", "donateurPrenom", true, "prenom du donateur");
	final private Option OPT_DON_SOMME = new Option ("s", "sommeDon", true, "Somme du don");
	private ProjetUtil projets;

	/**
	 * Démarre la commande
	 */
	public void runCommand () {
		this.projets = new ProjetUtil (); // Pour stocker les projets à un endroit centralisé
//		this.projets.addProjets (Projet.newPopProjets ()); // Peuplement automatique en dur
//		this.projets.addProjets (JacksonReader.run ("donations.json")); // Peuplement automatique

		System.out.println ("Bienvenue dans notre système de gestion de projets ! (c) Lucas Mariétan & Tanguy Genier - 2020");
		System.out.println ("Saisissez une commande (import, export, help ou exit pour quitter)");

		Scanner command = new Scanner (System.in);
		boolean running = true;
		while (running) {
			System.out.println ("Entrez votre commande : ");
			String com = command.nextLine ();
			String[] arguments = com.split (" ");
			CommandLine cmdLine = parseArguments (arguments);

			switch (cmdLine.getArgs ()[0].toLowerCase ()) {
				case CMD_ADD_DONATEUR:
					if (cmdLine.hasOption (OPT_PROJET.getOpt ()) && cmdLine.hasOption (OPT_DON_NOM.getOpt ()) && cmdLine.hasOption (OPT_DON_PRENOM.getOpt ()) && cmdLine.hasOption (OPT_DON_SOMME.getOpt ()))
						this.projets.addDonateur (cmdLine.getOptionValue (OPT_PROJET.getOpt ()), cmdLine.getOptionValue (OPT_DON_NOM.getOpt ()), cmdLine.getOptionValue (OPT_DON_PRENOM.getOpt ()), Long.parseLong (cmdLine.getOptionValue (OPT_DON_SOMME.getOpt ())));
					else
						printAppHelp ();
					break;

				case CMD_REMOVE_DONATEUR:
					if (cmdLine.hasOption (OPT_PROJET.getOpt ()) && cmdLine.hasOption (OPT_DON_NOM.getOpt ()) && cmdLine.hasOption (OPT_DON_PRENOM.getOpt ()))
						this.projets.removeDonateur (cmdLine.getOptionValue (OPT_PROJET.getOpt ()), cmdLine.getOptionValue (OPT_DON_NOM.getOpt ()), cmdLine.getOptionValue (OPT_DON_PRENOM.getOpt ()));
					else
						printAppHelp ();
					break;

				case CMD_IMPORT:
					if (cmdLine.hasOption (OPT_FICHIER.getOpt ())) {
						String fileName = cmdLine.getOptionValue (OPT_FICHIER.getOpt ());
//						Traitement du fichier en .json (c'était compliqué de comprendre l'erreur "\\.")
						if (fileName.split ("\\.")[1].equalsIgnoreCase ("JSON")) // On teste si le nom du fichier se termine par .json
							this.projets.addProjets (JacksonReader.run (fileName));
//						Traitement du fichier en .XML
						else if (fileName.split ("\\.")[1].equalsIgnoreCase ("XML"))
							this.projets.addProjets (MainUnmarshalling.run (fileName));
						else
							System.out.println ("Ce type de fichier n'est pas encore pris en compte");
					} else
						printAppHelp ();
					break;

				case CMD_EXPORT:
					if (cmdLine.hasOption (OPT_FICHIER.getOpt ()) && cmdLine.hasOption (OPT_PROJET.getOpt ())) {
						String fileName = cmdLine.getOptionValue (OPT_FICHIER.getOpt ());
						String projectName = cmdLine.getOptionValue (OPT_PROJET.getOpt ());

						if (projectName.equalsIgnoreCase ("ALL")) { // Exporter tous les projets dans un JSON
							if (fileName.split ("\\.")[1].equalsIgnoreCase ("JSON")) // On teste si le nom du fichier se termine par .json
								JacksonWriter.run (this.projets.toList (), fileName);
							else
								System.out.println ("Votre type de fichier n'est pas pris en charge...");
						} else {
							Projet exportProjet = this.projets.getProjet (projectName); // Recherche du projet dans le programme
							if (exportProjet != null) {
								if (fileName.split ("\\.")[1].equalsIgnoreCase ("JSON")) // On teste si le nom du fichier se termine par .json
									JacksonWriter.run (exportProjet, fileName);
								else
									System.out.println ("Votre type de fichier n'est pas pris en charge...");
							} else
								System.out.println ("Le projet désiré n'existe pas...");
						}
					} else
						printAppHelp ();
					break;

				case CMD_STATS:
					if (cmdLine.hasOption (OPT_PROJET.getOpt ())) {
						Projet exportProjet = this.projets.getProjet (cmdLine.getOptionValue (OPT_PROJET.getOpt ())); // Recherche du projet dans le programme
						if (exportProjet != null) {
							ExportToExcel.run (exportProjet);
							ExportToPDF.run (exportProjet);
						}
						else
							System.out.println ("Le projet désiré n'existe pas...");
					}
					else {
						ExportToExcel.run (this.projets.toList ());
					}
					break;

				case CMD_EXIT:
					System.out.println ("Fermeture !");
					running = false;
					break;

				case CMD_HELP:
					printAppHelp ();
					break;

				default:
					System.out.println ("Commande non reconnue !");
					break;
			}
		}
		command.close ();
	}

	/**
	 * Parses des arguments
	 *
	 * @param args application arguments
	 * @return <code>CommandLine</code> which represents a list of application
	 * arguments.
	 */
	private CommandLine parseArguments (String[] args) {
		Options options = getAllOptions ();
		CommandLine line = null;
		CommandLineParser parser = new DefaultParser ();

		try {
			line = parser.parse (options, args);
		} catch (ParseException ex) {
			System.err.println ("Erreur dans la lecture des arguments !");
			System.err.println (ex.toString ());
			printAppHelp ();
		}
		return line;
	}

	/**
	 * Generates application command line options
	 *
	 * @return application <code>Options</code>
	 */
	private Options getAllOptions () {
		Options options = new Options ();
		options.addOption (OPT_FICHIER).addOption (OPT_PROJET).addOption (OPT_DON_NOM).addOption (OPT_DON_PRENOM).addOption (OPT_DON_SOMME);
		return options;
	}

	/**
	 * Prints application help
	 */
	private void printAppHelp () {
		HelpFormatter formatter = new HelpFormatter ();
		formatter.printHelp (CMD_IMPORT, new Options ().addOption (OPT_FICHIER), true);
		formatter.printHelp (CMD_EXPORT, new Options ().addOption (OPT_FICHIER).addOption (OPT_PROJET), true);
		formatter.printHelp (CMD_ADD_DONATEUR, new Options ().addOption (OPT_PROJET).addOption (OPT_DON_NOM).addOption (OPT_DON_PRENOM).addOption (OPT_DON_SOMME), true);
		formatter.printHelp (CMD_REMOVE_DONATEUR, new Options ().addOption (OPT_PROJET).addOption (OPT_DON_NOM).addOption (OPT_DON_PRENOM), true);
		formatter.printHelp (CMD_EXIT, new Options ());
	}
}