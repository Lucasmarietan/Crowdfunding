package ch.hegarc.ig.business.JSON.jackson.object.reader;

public class MainObjectReader {


    private MainObjectReader() {
    }

    private void run() {
        DonationListBuilder donateurListBuilder = new DonationListBuilder();

        //Lit le message Json
        donateurListBuilder.readSource("Students.json");

        //Construit les objets métier
        donateurListBuilder.build();

        //Affichage console de notre objet métier classe
        System.out.println(donateurListBuilder.getDonateursList().toString());
    }

    public static void main(String[] args) {
        new MainObjectReader().run();
    }
}



