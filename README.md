# ADOM-projet

Projet d'ADOM du M2 Miage (s3)
Participants : *Aghiles Terbah, Malik Lela*



## Description générale

Le projet a été développé en respectant l'architecutre suivantes :
- Chaque paguetage correspond à un TP.
- Le paquetage fil.evaluation contient les classes qui permettent de générer les fichiers d'analyse pour les TP1, TP2 et TP3.


### Les fichiers générés

Pour chaque tp, des fichiers txt ont été générés et se trouvent dans le dossier `/Ressources/fichiersGeneres`.


### Analyse

Pour chaque TP, nous avons donc produit des fichiers textes qui seront analyser sur R.
Tous les scripts d'analyse se trouvent dans le dossier  `scriptsR`


## Explication du code

### TP1 - Algorithmes Gloutons

- Classe `Matrice` : A partir d'une instance, un chemin vers le dossier contenant toutes les instances, charge toutes les informations dans un tableau à 2 entrées.
- Classe `Glouton` : Contient toutes les fonctions necessaire pour l'algorthime Glouton. Possible de la tester en executant la classe.
- Il est possible de générer les fichiers dont il est question dans le rapport en executant la classe `fil.evaluation.EvaluationTP1`.


### TP2 - Algorithmes de recherche locale

- Classe `HillClimbing` : Contient toutes les fonctions dont il est question dans le tp2.
- Classe `Main` : Un cas d'utilisation simple.
- Il est possible de générer les fichiers dont il est question dans le rapport en executant la classe `fil.evaluation.EvaluationTP2`.


### TP3 - Algorithmes évolutionnaire

- Classe `Chemin` : Classe représentant un chemin (une liste d'entiers), son cout, un flag permettant de savoir si ce chemin a été visité, sa génération.
- Classe `Population` : Contient en fonction de la matrice, une liste de chemin (une population de base) qu'il est possible d'initialiser de façon aléatoire ou heuristique.
- Classe `OrderBasedCrossover` : Contient 2 attributs parents de type Chemin, et un attribut "enfant" qu'il est possible de générer par croisement via l'appel à la méthode **load**.
- Classe `Evolutionnaire` : Contient toutes les fonction necessaire au déroulement d'un algorithme évolutionnaire simple ou mémétique, il est possible de tester via la classe `Main` .
- Il est possible de générer les fichiers dont il est question dans le rapport en executant la classe `fil.evaluation.EvaluationTP3`.


### TP4 - Filtrage

- Classe `Filtre` : Classe abstraite représentant un filtre.
- Classe `MultiObjectiveObject` : Classe représentant un object à 2 critères
- Classe `TwoMatrice` : Classe représentant une instance (2 matrice).
- Classe `ParserMultiCriteres` : Classe contenant certaines fonctions utiles pour la suite ainsi que la méthode de test permettant de générer un fichier contenant des instances aléatoires.
- Classe `FiltreOffLine` : Classe représentant un filtre off-line, l'appel à la fonction **load** charge n **MultiObjectiveObject** pour chaque instance,
  les stock dans liste et marque les solutions dominées par l'appel à la méthode **FiltreByFlag**. L'appel à la fonction **filtreList** supprime alors toutes les solutions dominées des listes.
  Il est possible de tester et de générer les fichiers dont il est question dans le rapport en executant la méthode `main` de cette classe.
- Classe `FiltreOnLine` : Classe représentant un filtre on-line, l'appel à la fonction **load** permet de définir le nombre de solutions qui seront filtrées (et qu'on ne connait pas),
  cette fonction appelle toutes les autres, il ne restera ainsi que les solutions non-dominées.
  Il est possible de tester et de générer les fichiers dont il est question dans le rapport en executant la méthode `main` de cette classe.


### TP5 - Les fronts pareto

- Classe `Lambda` : Classe représentant un couple de poids.
- Classe `ApprocheScalaire` : Contenant toutes les fonctions necessaires à la mise en oeuvre de l'approche scalaire.
  Il est possible de tester et de générer les fichiers dont il est question dans le rapport en executant la méthode `main` de cette classe.
- Classe `ApprochePareto` : Contenant toutes les fonctions necessaires à la mise en oeuvre de l'approche pareto.
  Il est possible de tester et de générer les fichiers dont il est question dans le rapport en executant la méthode `main` de cette classe.
