# Prérequis

- sbt >= 1.6.0
- Java JDK 8+

# Run

````shell
sbt run
````

# Sujet

Il s'agit de créer un simple client CLI qui permet de chercher un ou des pokemons fournit dans le dossiers src/main/resources/pokemon.csv

Le CSV devra être chargé en mémoire au démarrage du programme. 
La structure de données pour le stockage en mémoire est libre (Array, List, Map, ...)

Les commandes à implémenter par le client CLI:

- GET <pokemon-name> 
  - Récupère un pokémon dans la DB et affiche ses caractèristique à la console.  Si il n'est pas trouvé, affiché une erreur
- GETALL <pokemon-type-1>
  - Affiche tous les pokémons qui appartiennent au type-1 (Affiche toutes les informations)
- COUNT <pokemon-type-1>
  - Affiche le nombre de pokémons qui appartiennent au type-1
- MATCH <keyword>
  - Affiche tous les noms de pokémons qui match avec le keyword. Si un nom de pokémon contient le <keyword> alors c'est un match
  
  WHEREIS <pokemon> Les deux commandes fonctionnent mais nous n'avont pas réussi a rendre un rendu propre de ces commandes, suite a de trop nombreuses difficultées avec spray
  MOVE <move name>

Vous avez le droit à la librairie standard de scala et java pour réaliser ce programme.
Essayer au maximum d'appliquer ce que vous avez appris de la programmation fonctionel. 

- Séparation entre fonction pure et impure
- Utilisation des méthodes des collections de scala (map, filter, foldLeft, ...)

## Resources

- https://docs.scala-lang.org/scala3/book/introduction.html
- https://docs.scala-lang.org/overviews/scala-book/command-line-io.html (Fonctionne aussi avec Scala 3)
- https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch12s02.html

# Examples

Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary
```shell
PokemonDB> GET Charmander
ID: 4
Name: Charamander
Type-1: Fire
Type-2: <None>
Total: 309
HP: 39
Attack: 52
Defense: 43
SP. Atk: 60
Def: 50
Speed: 65
Generation: 1
Legendary: False
```

```shell
PokemonDB> GETALL Fire
ID: 4
Name: Charamander
Type-1: Fire
Type-2: <None>
Total: 309
HP: 39
Attack: 52
Defense: 43
SP. Atk: 60
Def: 50
Speed: 65
Generation: 1
Legendary: False
---
ID: 5
Name: Charmeleon
Type-1: Fire
Type-2: <None>
Total: 405
HP: 58
Attack: 64
Defense: 58
SP. Atk: 80
Def: 65
Speed: 80
Generation: 1
Legendary: False
---
Etc...
```

```shell
PokemonDB> COUNT Fire
59
```

```shell
PokemonDB> MATCH Pid
Pidgey
Pidgeotto
Pidgeot
PidgeotMega
```

# Bonus

- Amélioration de getall pour pouvoir chercher des pokémons en utilisant d'autres champs (génération, type-2, legendary
- Match fonctionne avec des regex
- Ajout de pokémon
- Et tout ce qui peut vous passez par la tête