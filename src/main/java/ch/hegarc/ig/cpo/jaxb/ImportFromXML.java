package ch.hegarc.ig.cpo.jaxb;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.Projet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportFromXML {
	private static final Logger logger = Logger.getLogger (ImportFromXML.class.getName ());

	public static List <Projet> run (String fileName) {
		List <Projet> projetList = new LinkedList <> ();
		try {
			JAXBContext jc = JAXBContext.newInstance ("ch.hegarc.ig.cpo.jaxb");
			Unmarshaller unmarshaller = jc.createUnmarshaller ();
			XMLStreamReader in = XMLInputFactory.newInstance ().createXMLStreamReader (new FileInputStream (fileName));
			JAXBElement <Dataset> o = unmarshaller.unmarshal (in, Dataset.class);
			Dataset projets = o.getValue ();

//			Pour récupérer les projets du XML et les garder dans le programme
			for (Dataset.Record rec : projets.getRecord ()) {
				List<Donateur> donateurs = new LinkedList <> ();
				for (Dataset.Record.Donateurs d : rec.getDonateurs()){
					Donateur donateur = new Donateur (d.getId (), d.getPrenom (), d.getNom (), d.getEmail(), d.getLangue(), d.getAdresse(), d.getVille(), d.getMonnaie(), d.getSomme(), d.isPaye (), d.isAnnule (), d.getDateDon(), d.getDateVersement ());
					donateurs.add (donateur);
				}
				Projet projet = new Projet (rec.getId (), rec.getProjet (), donateurs);
				projetList.add(projet);
			}
			logger.log (Level.INFO, "Fichier '" + fileName + "' importé dans le programme avec succès ! ");
		} catch (Exception ex) {
			Logger.getLogger (ImportFromXML.class.getName ()).log (Level.SEVERE, null, ex);
		}
		return projetList;
	}
}