package ch.hegarc.ig;

public class Todo {

	/** Notes de développement de Tanguy
	 *
	 * Choix du Jackson pour import le Json car est le plus simple. Je me suis fortement inspiré de la série 5 pour construire le dossier Jackson
	 * JacksonWriter peremt d'exporter un projet avec ses donateurs. Faut-il changer de signature pour tout exporter ??
	 *
	 * J'ai généré le donations.xsd automatiquement, puis l'ai modififié (surtout pour enlever énumération...) mais çA générait 3 classes différentes
	 * J'ai pensé que c'était trop complexe. Alors j'ai généré le xsd sur https://www.freeformatter.com/xsd-generator.html. Puis j'ai généré le dossier jaxb avec Intellij
	 * TODO - Faut-il faire un binding personnalisé ???
	 * Création des packages (un)marshalling
	 */

    /** TODO
     *
     *Fonctionnalités précédentes :
     * - Lecture des données issues des fichiers JSON et XML
     * - Écriture des données dans un fichier JSON
     *
     *  Je suis parti sur l'utilisation de Jackson pour JSON car c'est le dernier chapitre que nous avons fait, et il me semble que c'est le plus simple d'utilisation.
     *  Je prends mes sources du corrigé de la série JSON avec Jackson
     *
     * Nouvelles fonctionnalités:
     * Au travers de méthodes et de classes de votre choix, implémenter les fonctionnalités suivantes au
     * projet Crowdfunding:
     * • Pouvoir fusionner les données provenant de différents fichiers (XML ou JSON). Chaque lecture
     * (import) de fichier doit fusionner avec les données existantes, si bien qu'il n'y a toujours qu'une
     * seule liste dans l'application.
     * Cette liste ne doit pas comporter de doublons, si par exemple des données identiques sont
     * dans plusieurs fichiers importés.
     * • Si cela n’est pas déjà fait, l'import doit pouvoir se faire via la console avec la commande
     * adéquate. (import -f [nom du fichier])
     * • L'écriture (ou export) doit produire un seul fichier JSON avec la commande adéquate (export -
     * f [nom du fichier] -p [nom du projet]). Si le nom d'un projet est mentionné, l'export ne contient
     * que les données du projet en question. Si l'utilisateur spécifie le projet "All", tous les projets
     * sont exportés (dans le même fichier).
     * • Pouvoir trier la liste des dons d'abords selon le nom du projet, puis au sein de chaque projet,
     * selon le nom et le prénom du donateur du donateur.
     * • Pouvoir ajouter un donateur sur un projet (en fournissant le nom du projet, le nom et prénom
     * du donateur et la somme du don). Une nouvelle commande (Console.class) doit être ajoutée
     * pour cette étape.
     * • Pouvoir supprimer un donateur d'une liste (selon le nom du projet, le nom et prénom du
     * donateur). Une nouvelle commande (Console.class) doit être ajoutée pour cette étape.
     *
     *
     *
     *
     */


}
