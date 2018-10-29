# Choix que nous faisons

- Liste d'évenement se construit au fur et à mesure -> plus de réactivité (robot en route vers un feu peu changer de cible par exemple.
- Robots commencent la simu vides (faire toggle?)
- calcul du plus court chemin -> A* ! stylé
- temps pour le calcul du pcc de type double ! plus de précision.
- temps pour les déplacements arrondis au supérieur
- Map de priorityQueue de drawables stylée ! (superposition par niveau!)
- Drawable en abstract class !

STRATEGIE
- les 'discrete events' sont gérés avec une machine d'état -> modifications ultérieures facilitées
- les transitions de la machine d'état sont stockées dans une "table vérité"
- la strategie est portee par le chef pompier. chaque chef a une strategie differente: plus le grade du chef est eleve, plus la strategie est evoluee
- lorsqu'un feu est eteint, tous les robot qui lui etaient affectes sont liberes en meme temps afin de permettre de futures strategies de groupe (impossible si les robots sont liberes un par un)
- le chef pompier affecte les feus aux robots, mais les robots vont se recharger en eau de maniere autonome (ils connaissent la carte et trouve le point d'eau le plus proche de leur position courante.)

OU ALORS
- le chef pompier sert juste à decider la cible du robot! (beaucoup plus facile et propre!)
fisrt class: premier feu, eau la plus proche
sergeant: feu le plus proche, eau la plus proche
captain: feu le plus proche, eau sur le chemin du feu le plus proche
major: strategie de robots : walking --> petits feux proches ? Drone Gros feux loin ?
colonel:
general: