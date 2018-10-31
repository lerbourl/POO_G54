# Choix que nous faisons

- Liste d'évenement se construit au fur et à mesure -> plus de réactivité (robot en route vers un feu peu changer de cible par exemple.
- Robots commencent la simu remplis (faire toggle?)
- calcul du plus court chemin -> A* ! stylé
- temps pour le calcul du pcc de type double ! plus de précision.
- temps pour les déplacements arrondis au supérieur
- Map de priorityQueue de drawables stylée ! (superposition par niveau!)
- Drawable en abstract class !
- les robots terrestres �teignent les feux depuis une case adjacente, sauf s'ils se trouvent d�j� sur le feu lors de l'affectation par le chef pompier.

STRATEGIE
- les 'discrete events' sont gérés avec une machine d'état -> modifications ultérieures facilitées
- les transitions de la machine d'état sont stockées dans une "table vérité"
- la strategie est portee par le chef pompier. chaque chef a une strategie differente: plus le grade du chef est eleve, plus la strategie est evoluee
- le chef pompier affecte les feus aux robots, mais les robots vont se recharger en eau de maniere autonome (ils connaissent la carte et trouve le point d'eau le plus proche de leur position courante.)
- le chef pompier sert juste à decider la cible du robot! (beaucoup plus facile et propre!)
fisrt class: premier feu, eau la plus proche
sergeant: feu qui a le moins de pompier affect�, eau la plus proche
captain: feu le plus proche, eau la plus proche
major: strategie d�pendante du robot : walking --> feu le plus proche, drone --> feu le plus loin, les autres -> feux qui ont le moins de pompiers affect�s
colonel:strategie d�pendante du robot : walking --> feu le plus proche, drone --> feu le plus loin des autres robots, les autres -> feux les plus proches des points d'eau. L'id�e est d'�viter les d�placements du robot � pied (tr�s lent), de minimiser les aller-retour des robots � roue et chenille (que se vident assez vite), et d'utiliser les drones pour atteindre les feux les plus isol�s (car ils volent rapidement).
general:
