package ch.hegarc.ig.util;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.unmarshalling.MainUnmarshalling;
import ch.hegarc.ig.util.jackson.JacksonReader;
import ch.hegarc.ig.util.jackson.JacksonWriter;
import org.apache.commons.cli.*;

import javax.rmi.PortableRemoteObject;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static ch.hegarc.ig.business.Projet.newPopProjets;

public class Console {
	private ProjetUtil projets;

	final private String CMD_IMPORT = "import";
	final private String CMD_EXPORT = "export";
//	Nouvelle commande
	final private String CMD_ADD_DONATEUR = "don";
	final private String CMD_STATS = "stats";
	final private String CMD_EXIT = "exit";

	final private Option OPT_FICHIER = new Option("f", "fichier", true, "nom du fichier");
	final private Option OPT_PROJET = new Option("p", "projet", true, "nom du projet");
//	Nouvelle options
	final private Option OPT_DON_NOM = new Option("n", "donateurNom", true, "nom du donateur");
	final private Option OPT_DON_PRENOM = new Option("r", "donateurPrenom", true, "prenom du donateur");

	/**
	 * Démarre la commande
	 */
	public void runCommand () {
//		Pour stocker les projets à un endroit centralisé
		this.projets = new ProjetUtil ();
		this.projets.addProjets (Projet.newPopProjets ());
		System.out.println ("Set initial");
		this.projets.addProjets (JacksonReader.run ("donations.json")); // Peuplement automatique
		for (Projet pj : this.projets.toList ())
			System.out.println (pj.toString (false));

		Scanner command = new Scanner(System.in);
		System.out.println("Entrer votre commande: ");

		boolean running = true;
		while (running) {
			String com = command.nextLine();
			String[] arguments = com.split(" ");
			CommandLine cmdLine = parseArguments(arguments);

			switch (cmdLine.getArgs()[0]) {
//				TODO - A chaque fois que je veux utiliser les options que j'ai ajouté ça merde...
				case CMD_ADD_DONATEUR:
					if (cmdLine.hasOption (OPT_PROJET.getOpt ()) && cmdLine.hasOption (OPT_DON_NOM.getOpt ()) && cmdLine.hasOption (OPT_DON_PRENOM.getOpt ())) {
					String donProjet = cmdLine.getOptionValue (OPT_PROJET.getOpt ());
					System.out.println ("nom : " + donProjet);
					String donNom = cmdLine.getOptionValue (OPT_DON_NOM.getOpt ());
					System.out.println ("nom : " + donNom);
					String donPrenom = cmdLine.getOptionValue (OPT_DON_PRENOM.getOpt ());
					System.out.println ("nom : " + donPrenom);
					} else {
						printAppHelp ();
					}
					break;

				case CMD_IMPORT:
					if (cmdLine.hasOption(OPT_FICHIER.getOpt())) {

						String fileName = cmdLine.getOptionValue (OPT_FICHIER.getOpt ());

//						Traitement du fichier en .json (c'était compliqué de comprendre l'erreur "\\.")
						if (fileName.split ("\\.")[1].equalsIgnoreCase ("JSON")) { // On teste si le nom du fichier se termine par .json
							this.projets.addProjets (JacksonReader.run (fileName));
							System.out.println ("Import du fichier " + fileName);
						}
//						Traitement du fichier en .XML
						else if (fileName.split ("\\.")[1].equalsIgnoreCase ("XML")) {
							this.projets.addProjets (MainUnmarshalling.run (fileName));
							System.out.println ("Import du fichier " + fileName);
							System.out.println ("-------");
							System.out.println (this.projets.toString ());
						}
						else {
							System.out.println ("Ce type de fichier n'est pas encore pris en compte");
						}
					} else {
						printAppHelp();
					}
					break;

				case CMD_EXPORT:
					if (cmdLine.hasOption(OPT_FICHIER.getOpt()) && cmdLine.hasOption(OPT_PROJET.getOpt())) {

						String fileName = cmdLine.getOptionValue (OPT_FICHIER.getOpt ());
						String projectName = cmdLine.getOptionValue (OPT_PROJET.getOpt ());

//						Pour tester le bon fonctionnement de JacksonWriter
//						TODO - Mettre ça au propre ou alors être sûr de comment l'utiliser
						if (projectName.equalsIgnoreCase ("ALL")) // Exporter tous les projets dans un JSON (pas de test de fichier)
							JacksonWriter.run (this.projets.toList (), fileName);
						else {
							if (this.projets.contient (projectName)) {
								if (fileName.split ("\\.")[1].equalsIgnoreCase ("JSON")) { // On teste si le nom du fichier se termine par .json
									JacksonWriter.run (this.projets.contientProjet (projectName), fileName);
									System.out.println ("Export du projet " + projectName + " dans le fichier " + fileName);
								} else if (fileName.split ("\\.")[1].equalsIgnoreCase ("XML")) {
//  								TODO - Faut-il vraiment faire ça ?
									System.out.println ("Le type de fichier XML n'est pas pris en charge...");
								} else {
									System.out.println ("Votre type de fichier n'est pas pris en charge...");
								}
							}
						}
					} else {
						printAppHelp();
					}
					break;

				case CMD_STATS:
/**					Ici on commence à tester CollectionUtil*/
//					List<Donateur> donateurs = CollectionUtil.plusGrosDonateur (this.projets.getProjet (1), 2);
//					String email = CollectionUtil.tousEmail (this.projets.getProjet (1));
//					System.out.println (email);
//					System.out.println ("Les donateurs qui n'ont pas paye : ");
//					List<Donateur> donateurs = CollectionUtil.pasEncorePaye (this.projets.getProjet (1));
//					for (Donateur d : donateurs)
//						System.out.println (d.toString ());
//					System.out.println ("Somme argent déjà payé : " + CollectionUtil.argentDejaPaye (this.projets.getProjet (1)));
//					System.out.println ("Somme argent encore à payer : " + CollectionUtil.argenrRestantAPaye (this.projets.getProjet (1)));
//					System.out.println ("Somme totale : " + CollectionUtil.argentTotal (this.projets.getProjet (1)));
//					System.out.println ("Commission : " + CollectionUtil.commission (this.projets.getProjet (1)));
//					System.out.println ("Mediane : " + CollectionUtil.medianeDons (this.projets.getProjet (1)));
//					System.out.println ("Moyenne : " + CollectionUtil.moyenneDons (this.projets.getProjet (1)));
//					System.out.println ("Total pour personnes concernees : " + CollectionUtil.totalDonsDonateurs (this.projets.toList (), "Meylan"));
//					this.projets.getProjet (1).triDonateurs ();
//					for (Donateur d : this.projets.getProjet (1).getDonateurs ())
//						System.out.println (d.toString ());
//					List<Donateur> donateurs = this.projets.pasEncorePaye ();
//					for (Donateur d : donateurs)
//						System.out.println (d.toString ());

//					this.projets.addProjet (new Projet (80, "Aaaaaa", null));
					System.out.println ("Après tir abc : enfin trié abc ?");
					for (Projet pj : this.projets.toList ())
						System.out.println (pj.toString (true));

					// TODO Calcul des stats des projets

					break;

				case CMD_EXIT:
					System.out.println("Fermeture!");
					running = false;
					break;

				default:
					System.out.println("Commande non reconnue!");
					break;
			}
		}
		command.close();
	}

	/**
	 * Parses des arguments
	 *
	 * @param args application arguments
	 * @return <code>CommandLine</code> which represents a list of application
	 * arguments.
	 */
	private CommandLine parseArguments(String[] args) {
		Options options = getAllOptions();
		CommandLine line = null;
		CommandLineParser parser = new DefaultParser();

		try {
			line = parser.parse(options, args);
		} catch (ParseException ex) {
			System.err.println("Erreur dans la lecture des arguments!");
			System.err.println(ex.toString());
			printAppHelp();
		}
		return line;
	}

	/**
	 * Generates application command line options
	 *
	 * @return application <code>Options</code>
	 */
	private Options getAllOptions() {
		Options options = new Options();
		options.addOption(OPT_FICHIER).addOption(OPT_PROJET);
		return options;
	}

	/**
	 * Prints application help
	 */
	private void printAppHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(CMD_IMPORT, new Options().addOption(OPT_FICHIER), true);
		formatter.printHelp(CMD_EXPORT, new Options().addOption(OPT_FICHIER).addOption(OPT_PROJET), true);
		formatter.printHelp(CMD_STATS, new Options().addOption(OPT_PROJET), true);
		formatter.printHelp(CMD_ADD_DONATEUR, new Options ().addOption (OPT_PROJET).addOption (OPT_DON_NOM).addOption (OPT_DON_PRENOM), true);
		formatter.printHelp(CMD_EXIT, new Options());
	}
}