# Choix que nous faisons

- Liste d'évenement se construit au fur et à mesure -> plus de réactivité (robot en route vers un feu peu changer de cible par exemple.
- Robots commencent la simu vides (faire toggle?)
- calcul du plus court chemin -> A* ! stylé
- temps pour le calcul du pcc de type double ! plus de précision.
- temps pour les déplacements arrondis au supérieur
- Map de priorityQueue de drawables stylée ! (superposition par niveau!)
- Drawable en abstract class !
- les 'discrete events' sont gérés avec une machine d'état -> modifications ultérieures facilitées
- les transitions de la machine d'état sont stockées dans une "table vérité"
- lorsqu'un feu est �teint, tous les robot qui lui �taient affect�s sont lib�r�s en m�me temps afin de permettre de futures strat�gies de groupe (impossible si les robots sont lib�r�s un par un)
- le chef pompier affecte les feus aux robots, mais les robots vont se recharger en eau de mani�re autonome (ils connaissent la carte et trouve le point d'eau le plus proche de leur position courante.)