# SubTranslate
Activité de fin du cours Java EE d'OpenClassrooms

Le dossier DB contient le fichier createDB.sql permettant la création de la base de données.
La base de données ainsi créé ne dispose pas de sous titre enregistré.
Pour charger un premier sous titre il faut utiliser l'application (voir ci dessous)

Dans l'application, l'accés à la base de donnée se fait avec le login 'utilisateur' et le mot de passe 'utilisateur',
Il faut donc également créer ce profil d'accés à la BDD avec les droits sur la base de données créé (translator)

===> La classe de lancement de l'application et la servlet Index.

L'application est découpée en trois partie constituant les 3 fonctionnalitées de celle-ci:

- Le premier formulaire permet le chargement de sous titre à partir d'un fichier SRT : (des fichiers SRT sont disponibles dans \WebContent\WEB-INF\SRT initiaux)
- Le deuxième formulaire permet d'effectuer la traduction d'un sous titre en choisissant le sous titre original et la langue de destination
	Ce formulaire amméne à la page de traduction, si une traduction à déjà été débutée dans la langue choisie, celle-ci peut être complétée et/ou modifiée.
- Le troisème formulaire permet le téléchargement du fichier SRT des sous titres enregistrés.

En complément le projet contient un diagramme UML des classes Java permettant peut être d'y voir plus clair dans l'organisation.
Ce diagramme a été réalisé avec Papyrus (sous Eclipse), sinon il est aussi visible au format png dans la racine du projet.

Merci d'avoir lu et bonne correction!!