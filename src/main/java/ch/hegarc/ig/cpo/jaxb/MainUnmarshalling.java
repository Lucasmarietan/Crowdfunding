package ch.hegarc.ig.cpo.jaxb;

// Classe prête et qui fonctionne !

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

public class MainUnmarshalling {
	private static final Logger logger = Logger.getLogger(MainUnmarshalling.class.getName());

	public static List<Projet> run (String fileName) {
		List<Projet> projetList = new LinkedList <> ();
		try {
			JAXBContext jc = JAXBContext.newInstance("ch.hegarc.ig.cpo.jaxb");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			XMLStreamReader in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream (fileName));
			JAXBElement <Dataset> o = (JAXBElement<Dataset>) unmarshaller.unmarshal(in, Dataset.class);
			Dataset projets = o.getValue();

//			Pour récupérer les projets du XML et les garder dans le programme
			for (Dataset.Record rec : projets.getRecord ())
				projetList.add (new Projet (rec.getId (), rec.getProjet (), rec.getDonateurs ()));

			logger.log (Level.INFO, "Fichier '" + fileName + "' importé dans le programme avec succès ! ");
		} catch (Exception ex) {
			Logger.getLogger(MainUnmarshalling.class.getName()).log(Level.SEVERE, null, ex);
		}
		return projetList;
	}
}