LOCATIONS
Les location contiennent des mtrices de cases
Une case possède un type de terrain.
Une case contient 1 item OU 1 character OU 1 exit OU 1 decor.

CHARACTERS
Les characters peuvent se déplacer

HERO
Le héro est déplacé sur la matrice (flèches directionnelles) OU pathfinding à la souris
Il possède une direction (top bot rgt lft) lui permettant d'interragir avec la cse à laquelle il fait face

INTERRACTION
Le but de la commande interraction est de réduire la charge de la vue et du controller
Une interraction se fait avec une case. En fonction de ce qu'elle contient, l'interraction appelle la commande adéquate

MENU COMMAND
Contient les commandes qui peuvent être appelées dans le menu : QUIT, HELP etc.

ITEMCOM
Contient les commandes utilisables sur les items (open sur les container et les autres sur items dns des containers)

MESSAGEMFR
Traduction des messages qui peuvent apparaître en jeu en français (sera fait à la fin en fonction des messages conservés

N.B : J'ai peut-être oublié des choses, le modèle est TOUJOURS modifiable et les modifications sont en cours donc dites moi