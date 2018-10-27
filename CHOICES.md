# Choix que nous faisons

- Liste d'Ã©venement se construit au fur et Ã  mesure -> plus de rÃ©activitÃ© (robot en route vers un feu peu changer de cible par exemple.
- Robots commencent la simu vides (faire toggle?)
- calcul du plus court chemin -> A* ! stylÃ©
- temps pour le calcul du pcc de type double ! plus de prÃ©cision.
- temps pour les dÃ©placements arrondis au supÃ©rieur
- Map de priorityQueue de drawables stylÃ©e ! (superposition par niveau!)
- Drawable en abstract class !
- les 'discrete events' sont gÃ©rÃ©s avec une machine d'Ã©tat -> modifications ultÃ©rieures facilitÃ©es
- les transitions de la machine d'Ã©tat sont stockÃ©es dans une "table vÃ©ritÃ©"
- lorsqu'un feu est éteint, tous les robot qui lui étaient affectés sont libérés en même temps afin de permettre de futures stratégies de groupe (impossible si les robots sont libérés un par un)
- le chef pompier affecte les feus aux robots, mais les robots vont se recharger en eau de manière autonome (ils connaissent la carte et trouve le point d'eau le plus proche de leur position courante.)