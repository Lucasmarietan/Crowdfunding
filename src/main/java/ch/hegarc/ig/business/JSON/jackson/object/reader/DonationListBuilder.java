package ch.hegarc.ig.business.JSON.jackson.object.reader;

import ch.hegarc.ig.business.Donateur;
import ch.hegarc.ig.business.DonateurList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DonationListBuilder {

    private DonateurList donateurs = null;
    private JsonObject messageJson = null;

    public DonationListBuilder() {
    }

    public void readSource(String filename) {
        try {
            try (JsonReader reader = Json.createReader(new FileReader(filename))) {
                messageJson = reader.readObject();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DonationListBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void build() {
        Donateur donateur;

        //Mon message JSON dans son ensemble représente une classe d'étudiants
        donateurs = new DonateurList(messageJson.getInt("id"), messageJson.getString("projet"));

        //L'attribut etudiants, dans notre objet, contient un tableau. On récupère donc ce tableau
        JsonArray tabDonateurs = messageJson.getJsonArray("donateurs");

        //on extrait la List<JsonObject> de notre tableau et on la parcours. Chaque élément du tableau est un objet JsonObject
        for (JsonObject donateurJson : tabDonateurs.getValuesAs(JsonObject.class)) {
            // Il est également possible de parcourir la liste de manière plus "standard"
            //   for (int i = 0; i< tabDonateurs.size(); i++) {
            //    JsonObject donateurJson = tabDonateurs.getJsonObject(i);
            donateur = new Donateur();
            donateur.setId(Integer.parseInt(donateurJson.getString("id")));
            donateur.setPrNom(donateurJson.getString("prenom"));
            donateur.setNom(donateurJson.getString("nom"));
            donateur.setEmail(donateurJson.getString("email"));
            donateur.setLangue(donateurJson.getString("langue"));
            donateur.setAdresse(donateurJson.getString("adresse"));
            donateur.setVille(donateurJson.getString("ville"));
            donateur.setMonnaie(donateurJson.getString("monnaie"));
            donateur.setSomme(donateurJson.getInt("somme")); //c'est un integer dans le fichier JSON mais un long dans Donateur du coup je sais pas si ca passe
           // donateur.set(donateurJson.getBoolean("paye"));
           // donateur.setMarried(donateurJson.getBoolean("annule"));
           // Il y a ces deux attribut en plus dans l'objet JSON mais je sais pas s'ils faut les utiliser ou non...
            donateur.setDateDon(donateurJson.getString("dateDon"));
            donateur.setDateVersement(donateurJson.getString("dateVersement"));

            donateurs.getDonateurs().add(donateur);
        }

        StringBuilder str = new StringBuilder();
        System.out.println(str);
    }

    public DonateurList getDonateursList() {
        return donateurs;
    }
}


