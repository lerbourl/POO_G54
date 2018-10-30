# TODO List ! Notez vos idÃ©es !

## LOUIS
- Tostring() override pour les dataclasses ! utile pour le debug !
- TOUT METTRE EN ANGLAIS
- RENDRE LE CODE PROPRE
- virer tout ce qui est points cardinaux ? on s'en fout ?
- Implementer les Ã©vÃ¨nements (move ok !)
- Chef robot !

## Alexandre
- le bug du chemin de longueur nulle est corrigé: dans le log, on voit explicitement que le robot reste sur place, et qu'on n'a pas rajouté de temps de déplacement dans sa file d'événements.
MAIS je ne corrige volontairement pas le bug du robot qui se trouve déjà sur une tuile feu (après avoir éteint un autre feu ou après avoir fait le plein). L'animation est trop bizarre sinon: le robot se décale de côté pour éteindre le feu, mais retraverse le feu pour aller vers le point d'eau... 