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