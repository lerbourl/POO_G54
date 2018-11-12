# PROJET ROBOTS POMPIERS
Un projet Ensimag. POO en java, équipe de robots pompiers et extinction d'incendies virtuels.

## Introduction

Notre programme implémente la simulation graphique de l'extinctions d'incendies sur un carte par différents type de robots. Différentes stratégies de solutions sont mises en place.

## Executer le programme

### Prérequis

#### ant

Les instructions de compilation et d'exécution sont écrites dans le fichier build.xml ! Vous aurez besoin de Ant pour lancer notre programme. Vous pouvez installer ant avec votre gestionnaire de paquets préferé. Pour Ubuntu :
```
sudo apt-get install ant
```

#### java

Et oui, vous avez besoin d'une version de java installée pour executer nos programmes.

### Comment commencer ?

Depuis un terminal, se positionner astucieusement à la racine de l'archive grâce à la commande `cd`, puis tapper simplement la commande `ant` ou `ant info`, et suivez les instruction.

### Quelle carte utiliser ?

Avec le bon format, vous pouvez vous-même créer vos propres cartes de test, ou bien utiliser les cartes dans le dossier "cartes". Astuce : donnez à ant le chemin relatif depuis la racine (cartes/lacarte.map).

### Quelle stratégie utiliser .

Voir le dossier pdf pour une explication des différentes stratégies de résolution du problème. En résumé : Captain emmène simplement un robot au feu le plus proche, et colonel est la stratégie la plus avancée.

## Les tests

Amusez-vous à tester différentes stratégies sur les différentes cartes. Les résultats de vitesse de résolution du problème sont consignés dans le fichier BENCHMARK.


## Exécution et compilation

L’initialisation de notre simulateur nécessite trois paramètres: le chemin de la carte, la stratégie du chef pompier et un facteur de vitesse permettant d’accélérer la simulation. Lire le README pour plus de détails (si vous ne l’aviez pas encore compris)

En cas de paramètre invalide, le simulateur rappellera la syntaxe correcte:

```
Syntax: java SolveMapWithStrategy <map_file_path> <fireman_rank> <speedup_factor>
<map_file_path>: path to .map file
<fireman_rank>: first_class / sergeant / captain / major / colonel / general

The firefighters' strategy depends on the rank of their chief:
    [first_class]: all robots are affected to the 1st available fire
    [sergeant]: a robot is assigned to the 1st unassigned fire or the fire with the fewest assigned robots
    [captain]: a robot is assigned to its closest fire
    [major]: Walking robots assigned to their closest fires, Drone robots assigned to their farthest fires, other robots assigned to fires with the fewest affected robots
    [colonel]: Walking robots assigned to their closest fires, Drone robots assigned to the most isolated fires, other robots assigned to the fires closest to water tiles

<speedup_factor>: factor that accelerates the simulation.
```
## Les outils supplementaires

###Eclipse
Le code est tapé/cliqué sur l’IDE Eclipse, très utile sous plusieurs aspects.


###ObjectAID
Les diagrammes des classes UML ont été générés à l’aide du plug-in ObjectAID UML explorer. Cet outil s’intègre dans l’environnement Eclipse: http://www.objectaid.com/class-diagram.
