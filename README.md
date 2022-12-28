# projet_commun_nfa032_2022

Projet de gestion d’un Annuaire en ligne de commande

Le but de ce projet est de développer une application Java qui effectue une gestion allégée d’un Annuaire simplifié et en ligne de commande,
un peu inspiré de pages blanches : https://www.pagesjaunes.fr/pagesblanches
Lorsque l’application est lancée, elle doit afficher sur la console un menu de la forme :

    Bienvenue dans l’Annuaire NFA032

Administrateur :
1. Ajouter une personne
  A. Ajouter un Admin
  B. Ajouter un Utilisateur
  
Utilisateur :
2. Rechercher un ou des Utilisateurs
  A. Par nom
  B. Par email
  C. Par profil
3. Modifier mes informations personnelles

Votre choix : ___

Les chiffres et les lettres précédent chaque item doivent correspondre à ce que l’utilisateur de l’application
doit saisir sur le clavier pour accéder à la fonctionnalité proprement dite.
Exemple : 
En tant qu’administrateur, si je veux ajouter un nouvel utilisateur dans l’annuaire, je dois, 
à partir du menu, saisir 1 au clavier, puis le programme doit me demander de choisir entre les caractères 
A ou B afin de me proposer d’ajouter un Admin ou un Utilisateur.

Concernant la persistance des données enregistrées par le programme, il faut les stocker dans des fichiers texte, csv ou autre.
Typiquement, il faut gérer 2 fichiers : un fichier comptes et un fichier annuaire.

Fichier de comptes : email;mdp;rôle
Fichier Annuaire : nom;prenom;email;adressePostale;dateNaissance;telephone;dateAjout;dateMaj

Le fichier de gestion des comptes a pour objectif de stocker les informations sur les comptes des administrateurs et des utilisateurs.
Avant qu'il puisse modifier ses infos personnelles, le programme doit demander à l’utilisateur de s’authentifier avec son email et le mot de passe 
qui aura été crée pour lui au moment de l’ajout via le Menu 1.

Le fichier de comptes stocke donc sur chaque ligne, un compte par utilisateur de l’application.
Un compte est constitué de l’email de l’utilisateur, son mot de passe et le rôle de la personne ajoutée.
Il y a 2 types de rôles : le rôle admin (administrateur) et le rôle user (utilisateur).
Chacune de ces données peuvent être séparées par des points virgules (;), ça aidera à traiter aisément les informations dans le programme 
quand il faudra lire et parser chaque ligne du fichier.

Il faudra faire pareil pour le fichier Annuaire en respectant les données à enregistrer (comme illustré dans le tableau ci-dessus.)
Au moment du développement de l'application, prévoir dans le fichier comptes un administrateur par défaut, par exemple: admin@cnam.fr;admin;admin, 
qui sera l’administrateur original pouvant en ajouter d’autres. Tous les administrateurs auront les mêmes droits.

Règles de gestion :
I - Ajout d’un particulier
- Seul un compte administrateur a le droit d’ajouter une particulier dans l’annuaire
- Avant d’ajouter un nouveau particulier dans l’annuaire, l’application doit vérifier qu’un compte avec la même adresse email n’existe pas déjà ;
  si oui -> echec ; si non -> Ajout
- L’ajout d’un particulier dans le fichier annuaire doit être suivi par l’ajout d’un compte dans le fichier des comptes
- Durant le processus d’ajout d’un particulier, le programme doit demander tour à tour à l’administrateur de saisir les informations suivantes concernant ce particulier :
  nom, prénom, email, adresse postal, date de naissance, profil, date d’ajout, date de modification. 
  Au moment d’un primo ajout, la date d’ajout est égale à la date de modification. 
  Le profil correspond en quelque sorte à la catégorie professionnelle du particulier. 
  Vous pouvez gérer pour ce programme trois types profils : Auditeurs, Enseignants, Direction
- Avant toute opération d’ajout, le programme doit demander à l’administrateur de saisir son email et son mot de passe pour vérifier ses droits 
  avant de le laisser continuer ou pas
    
                                                                                /-> Oui -> Echec
Début -> Authentification -> Ajout Utilisateur -> Check si Utilisateur existe -                                     /-> Fichier Annuaire
                                                                                \-> Non -> Enregistrer Utilisateur -
                                                                                                                    \-> Fichier Comptes

II- Ajout d’un administrateur
- Seul un compte administrateur peut en ajouter un autre
- Lorsqu’on ajoute un administrateur, on doit vérifier que le compte n’existe pas déjà à travers le contrôle de son adresse email
- L’ajout d’un administrateur se fait uniquement dans le fichier des comptes
- Avant toute opération d’ajout, le programme doit demander à l’utilisateur de saisir son email et son mot de passe pour vérifier 
  ses droits avant de le laisser continuer ou pas
  
III- Recherche d’un utilisateur
Tout le monde (administrateur ou utilisateur) a le droit d’effectuer des recherches dans l’annuaire, donc et pas besoin d'authentification.
Trois types de recherches sont possibles :
- Par nom : possibilité de remonter plusieurs résultats. Si plusieurs particuliers match la recherche, remonter les 10 derniers récemment ajoutés
- Par email : doit normalement remonter un seul résultat
- Par profil : possibilité de remonter plusieurs résultats. Si plusieurs particuliers match la recherche, remonter les 10 derniers récemment ajoutés
La liste des résultats pour les opérations de recherche depuis le fichier annuaire doit être formatée de façon à permettre 
un affichage agréable et aisément lisible sur la console. De plus, les résultats doivent être affichés par ordre croissant des noms de particuliers.

IV- Modification d’un particulier
- Un particulier a le droit de modifier uniquement les informations le concernant dans le fichier annuaire.
  Mais tout administrateur peut modifier n’importe quel particulier dans l’annuaire
- Avant d’effectuer une opération de modification, le programme doit demander à l’utilisateur de s’authentifier avec son email et son mot de passe.
  Il doit donc vérifier que ce compte existe et qu'il est lié à une ligne dans le fichier annuaire, 
  puis lui donner la possibilité de re-saisir ses données (même processus que l’ajout d’un particulier, sauf la création du mot de passe). 
  Un particulier ne peut modifier son propre mot de passe.
- La modification doit impliquer la mise à jour de la date de modification dans le fichier annuaire.

Note Générale :
Après toute opération (Ajout, Recherche, Modification), le programme doit revenir au menu principal afin de demander l’éventualité d’une nouvelle opération.

En somme, il faut utiliser tous les concepts de la POO étudiés, pertinents pour concevoir un programme robuste, 
lisible, maintenable, efficace et capable de gérer toutes les erreurs possibles (héritage, 
polymorphisme, package, Exception, entrée/sortie, types de données, interfaces, tri, etc).
