# Jeu de plateforme HOP 

- Langage : JAVA
- Contributeurs : - RAMANANKIEFERANA Lafatra Julio (groupe 1) et QIU Lorie (groupe 3)

Hop est un jeu de plateforme où le joueur se retrouve à sauter de blocs en blocs 
afin de rester en vie et ne pas tomber dans le vide.
Le joueur incarne le personnage d'Axel, qui est un chat ayant comme défi de ne pas 
succomber aux forces de la gravité tout en faisant grimper son score. 
Ce projet inclut plusieurs autres mécanismes permettant de faire fonctionner le jeu.

Voici les fonctionnalités que nous avons implanté dans le jeu :
-


* Un défilement vertical continu : Les blocs ainsi que l'environnement de jeu défilent,
  faisant apparaître des blocs de haut en bas. Axel doit sauter de blocs en blocs afin
  d'échapper au défilement qui l'attire vers le bas

  Système de niveaux : Il y a en tout 7 niveaux atteignables sur le jeu. Plus un niveau est
  élevé, plus le défilement est rapide et plus le jeu est difficile.

* Des bonus : Collecter des bonus en forme de fraise permet d'incrémenter le score de 100 points,
* ce qui aide à faire passer au niveau supérieur.

* Des pièges : Plus un niveau est élevé, plus les blocs rétrécissent en largeur. De plus,
* à partir du niveau 3, certains blocs sont mobiles et vont de droite à gauche, rendant le jeu
* plus difficile.

* Menu principal : Un menu principal est disponible afin de lancer le jeu ou le quitter.

* Sons : Une musique de fond est jouée en boucle du début jusqu'à la mort d'Axel. Il y a
* également un son lors de la mort d'Axel (miaulement de chat).

Contrôles du jeu 
-
* Flèche du haut : sauter
* Flèche du bas : plonger
* Flèche droite : aller à droite
* Flèche gauche : aller à gauche

Dépendances
-
Aucune bibliothèque hors les bibliothèques standard de Java ne sont requises.

Structure du projet
-
* Axel.java : Gère le personnage principal, ses mouvements et ses interactions.

* Field.java : Gère l'environnement du jeu, y compris les blocs, les bonus et les pièges.

* Block.java : Définit les blocs

* MovingBlock : Définit les blocs mobiles

* Bonus.java : Définit les bonus à collecter.

* Sound.java : Gère la musique de fond et les effets sonores.

* GamePanel.java : Gère l'affichage graphique et les entrées clavier.

* Hop.java : Classe principale qui initialise le jeu et gère le menu principal.

* MainMenu.java : Affichage du menu principal par lequel on lance/quitte le jeu.

