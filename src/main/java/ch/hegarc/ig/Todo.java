package ch.hegarc.ig;

public class Todo {

	/** Notes de développement de Tanguy
	 *
	 * TODO Gestion d'un projet avec des espaces dans le nom
	 *      Ajout de fonctionnalité Logger sur les autres classe ??
	 *
	 * Choix du Jackson pour import le Json car est le plus simple. Je me suis fortement inspiré de la série 5 pour construire le dossier Jackson
	 * JacksonWriter peremt d'exporter un projet avec ses donateurs. Faut-il changer de signature pour tout exporter ??
	 *
	 * Pour le marshalling et unmarshalling du XML, je me suis fortement inspiré de la série 3
	 * J'ai généré le donations.xsd automatiquement, puis l'ai modififié (surtout pour enlever énumération...) mais ça générait 3 classes différentes
	 * J'ai pensé que c'était trop complexe. Alors j'ai généré le xsd sur https://www.freeformatter.com/xsd-generator.html. Puis j'ai généré le dossier jaxb avec Intellij
	 * Création des packages (un)marshalling
	 *
	 * Ajout d'un argument pour imprimer les donateurs dans les projets
	 *
	 * Classe ProjetUtil pour gérer la "persistance" des données. Avec les méthodes spécifiques pour les trier par exemple.
	 * Dans la console, on traite la gestion de ce ProjetUtil
	 *
	 * Tentative de tri des projets selon l'ordre alphabétique avec un stream mais fonctionnait pas car un HashSet ne gère pas ceci.
	 * Avec un TreeSet, en ajoutant Comparable à la classe Projet ainsi que l'implémentation de CompareTo, le problème a été reglé.
	 * Donc pas de méthode triAlphabetique dans ProjetUtil
	 * Tri des donateurs réussi !
	 *
	 * Commencer à ajouter la nouvelle commande pour ajouter un donateur à un projet mais ça plante... sûrement dû à mes options...
	 *
	 * Ajout de la classe CollectionUtil et ses méthodes pour traiter les projets et les donateurs
	 *
	 * Ajout des attributs booleens paye et annulé dans la classe Donateur. Le json les récupère automaatiquement mnt
	 *
	 */

	/**
	 **** Semaine 1 : *****
	 * Lecture/Ecriture JSON/XML
	 *      La première étape que vous devez développer concernant la lecture et l’écriture de document JSON et XML.
	 *       >>> En ajoutant les classes et méthodes nécessaires, faites en sorte de pouvoir lire les fichiers donations.json et donations.xml qui se trouve à la racine du projet. Dans un premier temps, vous n’êtes pas obligé d’utiliser le système de console mis en place même si cela devra être le cas à terme.
	 *     On sait lire le fichier XML mais pas le mettre dans ProjetUtil. A faire grace au JAXB et personnal binding
	 *
	 *       >>> Dans un second temps, ajouter les classes et méthodes permettant l’écriture d’un fichier JSON avec les classes métiers proposés.
	 * TODO     Problème permanent mdrr : Structurer votre application et vos fichiers afin d’avoir une bonne lisibilité et maintenabilité.
	 *
	 **** Semaine 2 : *****
	 * Au  travers de méthodes et de classes de votre choix, implémenter les fonctionnalités suivantesau projet Crowdfunding :
	 *      Pouvoir fusionner les données provenant de différents fichiers (XML ou JSON). Chaque lecture (import) de fichier doit fusionner avec les données existantes, si bien qu'il n'y a toujours qu'une seule liste dans l'application.
	 *      Cette  liste  ne  doit  pas  comporter  de  doublons,  si  par  exemple  des  données  identiques  sont dans plusieurs fichiers importés.
	 * Si même nom de projet, alors on fusionne la liste des dons. Si donateur existe déjà dans le projet, alors ?
	 *
	 **      Si  cela  n’est  pas  déjà  fait,  l'import  doit  pouvoir  se  faire  via  la  console  avec  la  commande adéquate. (import -f [nom du fichier])
	 *
	 **      L'écriture (ou export) doit produire un seul fichier JSON avec la commande adéquate (export -f [nom du fichier] -p [nom du projet]). Si le nom d'un projet est mentionné, l'export ne contient que les données du projet en question. Si l'utilisateur spécifie le projet "All", tous les projets sont exportés (dans le même fichier).
	 *
	 **      Pouvoir trier la liste des dons d'abords selon le nom du projet, puis au sein de chaque projet, selon le nom et le prénom du donateur du donateur. PAR DEFAUT !
	 *
	 * Pouvoir ajouter un donateur sur un projet (en fournissant le nom du projet, le nom et prénom du donateur et la somme du don). Une nouvelle commande (Console.class) doit être ajoutée pour cette étape
	 * TODO  Gérer l'appel dans la Console de ProjetUtil.class : addDonateur (String projetName, String nom, String prenom, long somme)
	 *
	 * Pouvoir  supprimer  un  donateur  d'une  liste  (selon  le  nom  du  projet,  le  nom  et  prénom  du donateur). Une nouvelle commande (Console.class) doit être ajoutée pour cette étape.
	 * TODO  Gérer l'appel dans la Console ProjetUtil.class : removeDonateur (String projetName, String nom, String prenom)
	 *
	 ***** Semaine 2 : *****
	 * Au  travers  de  méthodes  et  de  classes  de  votre  choix,  implémenter  les  fonctionnalités  suivantes au projet Crowdfunding:
	 **      Lister les 5 plus gros donateurs d'un projet donnée en argument(Nom, Prénom, Montant)
	 *
	 **       Lister les donateurs n'ayant pas encore payé leur don (non annulé)(Nom, Prénom, Montant)
	 *
	 **       La somme d'argent déjà payé, la somme restant à payer et la somme totale des dons pour un projet donné en argument.
	 *
	 **       Donner la liste de tous les mails des donateurs d’un projet(en argument), séparés par un «;»
	 *
	 **       Donner les médianes et moyennes des dons pour unprojetdonné en argument
	 *
	 * TODO  Pour une liste de noms séparés par des virgules et reçu en argument, calculer le total des dons pour ces personnes, quelque soit le projet
	 *
	 *        Calculer la commission de l’entreprise pour un projet donné (5%)
	 *
	 *
	 */
}