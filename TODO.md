# TODO List ! Notez vos idées !

## LOUIS
- Tostring() override pour les dataclasses ! utile pour le debug !
- TOUT METTRE EN ANGLAIS
- RENDRE LE CODE PROPRE
- virer tout ce qui est points cardinaux ? on s'en fout ?
- Implementer les évènements (move ok !)
- Chef robot !

## Alexandre
- le bug du chemin de longueur nulle est corrig�: dans le log, on voit explicitement que le robot reste sur place, et qu'on n'a pas rajout� de temps de d�placement dans sa file d'�v�nements.
MAIS je ne corrige volontairement pas le bug du robot qui se trouve d�j� sur une tuile feu (apr�s avoir �teint un autre feu ou apr�s avoir fait le plein). L'animation est trop bizarre sinon: le robot se d�cale de c�t� pour �teindre le feu, mais retraverse le feu pour aller vers le point d'eau... 