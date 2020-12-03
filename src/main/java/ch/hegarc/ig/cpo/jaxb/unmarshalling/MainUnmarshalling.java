package ch.hegarc.ig.cpo.jaxb.unmarshalling;

//	TODO - Faire pour stocker les données de manière persistante et globale au projet
//  Car Dans Record, la liste de Donateur n'est pas. C'est des Donateurs. Faire un Binding perso pour ça ?

//  Pour l'instant, il sait juste lire le XML (et afficher les projets avec ses donateurs)

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;
import ch.hegarc.ig.cpo.jaxb.Dataset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUnmarshalling {

	private static final Logger logger = Logger.getLogger(MainUnmarshalling.class.getName());

	private MainUnmarshalling() {
	}

	public static List<Projet> run (String fileName) {
		List<Projet> projetList = new LinkedList <> ();
		try {
			JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");

			Unmarshaller unmarshaller = jc.createUnmarshaller();

			XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream (fileName));

			JAXBElement <Dataset> o = (JAXBElement<Dataset>) unmarshaller.unmarshal(in, Dataset.class);

			Dataset projets = o.getValue();

//			Pour récupérer les projets dans le XML
			for (Dataset.Record rec : projets.getRecord ()) {
				List<Donateur> donateurList = new ArrayList <> ();
//				for Dataset.Record.Donateurs d : rec.getDonateurs ()) {
//					donateurList.add (d);
				Projet projet = new Projet (rec.getId (), rec.getProjet ().toString (), rec.getDonateurs ());
				projetList.add (projet);
//				System.out.println ("Nom du projet : " + rec.getProjet () + " (id : " + rec.getId () + "). Les donateurs : ");
//				for (Dataset.Record.Donateurs d : rec.getDonateurs ()) {
//					StringBuilder sb = new StringBuilder ();
//					sb.append ("    Nom : ").append (d.getNom ()).append (", ").append (d.getPrenom ()).append (". Somme : ").append (d.getSomme ()).append (" ").append (d.getMonnaie ());
//					System.out.println (sb.toString ());
//				}
			}

//			for (Dataset.Record rec : projets.getRecord ()) {
//				logger.log(Level.INFO, "{0} - {1}", new Object[]{rec.getProjet (), rec.getProjet (), rec.getDonateurs ()});
//			}
		} catch (Exception ex) {
			Logger.getLogger(MainUnmarshalling.class.getName()).log(Level.SEVERE, null, ex);
		}
		return projetList;
	}
}