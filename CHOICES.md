# Choix que nous faisons

- Liste d'Ã©venement se construit au fur et Ã  mesure -> plus de rÃ©activitÃ© (robot en route vers un feu peu changer de cible par exemple.
- Robots commencent la simu remplis (faire toggle?)
- calcul du plus court chemin -> A* ! stylÃ©
- temps pour le calcul du pcc de type double ! plus de prÃ©cision.
- temps pour les dÃ©placements arrondis au supÃ©rieur
- Map de priorityQueue de drawables stylÃ©e ! (superposition par niveau!)
- Drawable en abstract class !
- les robots terrestres éteignent les feux depuis une case adjacente, sauf s'ils se trouvent déjà sur le feu lors de l'affectation par le chef pompier.

STRATEGIE
- les 'discrete events' sont gÃ©rÃ©s avec une machine d'Ã©tat -> modifications ultÃ©rieures facilitÃ©es
- les transitions de la machine d'Ã©tat sont stockÃ©es dans une "table vÃ©ritÃ©"
- la strategie est portee par le chef pompier. chaque chef a une strategie differente: plus le grade du chef est eleve, plus la strategie est evoluee
- le chef pompier affecte les feus aux robots, mais les robots vont se recharger en eau de maniere autonome (ils connaissent la carte et trouve le point d'eau le plus proche de leur position courante.)
- le chef pompier sert juste Ã  decider la cible du robot! (beaucoup plus facile et propre!)
fisrt class: premier feu, eau la plus proche
sergeant: feu qui a le moins de pompier affecté, eau la plus proche
captain: feu le plus proche, eau la plus proche
major: strategie dépendante du robot : walking --> feu le plus proche, drone --> feu le plus loin, les autres -> feux qui ont le moins de pompiers affectés
colonel:strategie dépendante du robot : walking --> feu le plus proche, drone --> feu le plus loin des autres robots, les autres -> feux les plus proches des points d'eau. L'idée est d'éviter les déplacements du robot à pied (très lent), de minimiser les aller-retour des robots à roue et chenille (que se vident assez vite), et d'utiliser les drones pour atteindre les feux les plus isolés (car ils volent rapidement).
general:
