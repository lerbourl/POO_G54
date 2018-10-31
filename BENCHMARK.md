# BENCHMARK STRATEGIES
## MAP carteSujet.map
Strategy			Duration
-first_class		24416
-sergeant			23934
-captain			21938
-major				19234
-colonel			15632


## MAP desertOfDeath-20x20.map
Strategy			Duration
-first_class		17660
-sergeant			17552
-captain			17318
-major				17617
-colonel			16295

## MAP mushroomOfHell-20x20.map
Strategy			Duration		Note
-first_class		20198			Le robot � roue se coince sur une tuile for�t d�s le d�but
-sergeant			16152			Le robot � roue se coince sur une tuile for�t
-captain			12143			Le robot � roue se coince sur une tuile for�t pour le dernier feu
-major				15884			Le robot � roue se coince sur une tuile for�t d�s le d�but
-colonel			11552			Le robot ne se coince pas car les drones commencent par les feux de for�t

## MAP spiralOfMadness-50x50.map
Strategy			Duration		Note
-first_class		54124			Il n'y a que des feux de for�t inatteignables pour le robot � roue
-sergeant			52687
-captain			51644
-major				53199
-colonel			51720			La strat�gie colonel est moins efficace s'il n'y a pas de drone.
									Simulation extremement lente car beaucoup de tuiles eau!									
									-> diminuer le coup de l'algo de recherche du feu le plus proche d'un point d'eau