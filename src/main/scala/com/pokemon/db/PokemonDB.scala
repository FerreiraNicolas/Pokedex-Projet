package com.pokemon.db
import scala.io.Source
import monix.eval.Task
import scala.io.StdIn.readLine
import spray.json._
import DefaultJsonProtocol._

object PokemonDB:

  def choice(x:Double, y:Double) = if (y == 0) None else Some(x/y)

  def isBoolean(in: String): Boolean = {
    try {
      in.toBoolean
      true
    } catch {
      case e: Exception => false
    }
  }

  def isInteger(in: String): Boolean = { 
    try { 
      Integer.parseInt(in.trim)
      true
    } catch { 
      case e: Exception => false
    }
  }



  def readCSV(filename: String) : List[List[String]] = {
    io.Source.fromFile(filename)
      .getLines()
      .drop(1)
      .map(line => line.split(",").toList)
      .toList
  }

  def parsePokemons(lines: List[List[String]]): Either[PokemonError, List[Pokemon]] = {
    lines.map(
      line => 
      parsePokemonAttributes(line) 
    )
    .foldLeft(Right(List.empty[Pokemon]):Either[PokemonError,List[Pokemon]]) {
      case (Right (list), Right (n)) => {
        Right (n :: list)
      } 
      case (Right(_), Left(err)) => Left(err)
      case (acc,_) => acc
    } 
  }

  def parsePokemonAttributes(attributes: List[String]): Either[PokemonError, Pokemon] = {
    if(isInteger(attributes(4)) &&
      isInteger(attributes(5)) &&
      isInteger(attributes(6)) &&
      isInteger(attributes(7)) &&
      isInteger(attributes(8)) &&
      isInteger(attributes(9)) &&
      isInteger(attributes(10)) &&
      isInteger(attributes(11)) &&
      isBoolean(attributes(12))&&
      attributes.length == 13){
      Right(Pokemon(
        id = attributes(0).toInt,
        name = attributes(1),
        type1 = attributes(2),
        type2 = attributes(3),
        total = attributes(4).toInt,
        hp = attributes(5).toInt, 
        attack = attributes(6).toInt,
        defense = attributes(7).toInt,
        spAttack = attributes(8).toInt,
        spDefense = attributes(9).toInt,
        speed = attributes(10).toInt,
        generation = attributes(11).toInt,
        legendary = attributes(12).toBoolean
      ))
    } else {
      Left(PokemonError("Error while parsing a pokemon"))
    }
  }

//- GET <pokemon-name> 
//  - Récupère un pokémon dans la Base de donnée et affiche ses caractèristique à la console.  Si il n'est pas trouvé, affiche une erreur
  def getPokemon(name: String, pokemons : List[Pokemon]): Pokemon = {
    pokemons.find(_.name == name).get
  }

//- GETALL <pokemon-type-1>
//  - Affiche tous les pokémons qui appartiennent au premier type du pokemon (Affiche toutes les informations)
  def getAllPokemonFromType(type1: String, pokemons : List[Pokemon]): List[Pokemon] = {
    pokemons.filter(_.type1 == type1)
  }

  //- GETALLGENERATION <Num de la gen du pokemon>
  //  - Affiche tous les pokémons qui appartiennent à  la génération séléctionée (Affiche toutes les informations)
  def getAllPokemonFromGeneration(generation: Int, pokemons: List[Pokemon]): List[Pokemon] = {
  pokemons.filter(_.generation == generation)
}

  //- GETALLLEGENDARY <True/False>
  //  - Affiche tous les pokémons qui sont Légendaire ou non (Affiche toutes les informations)
  def getAllPokemonFromLegendary(legendary: String, pokemons: List[Pokemon]): List[Pokemon] = {
      pokemons.filter(_.legendary == legendary.toBoolean)
  }
//- COUNT <pokemon-type-1>
//  - Affiche le nombre de pokémons qui appartiennent au premier type du pokemon par exemple Fire
  def countPokemonFromType(type1: String, pokemons : List[Pokemon]): Int = {
    pokemons.count(_.type1 == type1)
  }

//- MATCH <keyword>
//  - Affiche tous les noms de pokémons qui match avec le keyword. Si un nom de pokémon contient le <keyword> alors c'est un match
  def matchPokemon(keyword: String, pokemons : List[Pokemon]): List[String] = {
    pokemons.filter(_.name.contains(keyword)).map(_.name)
  }

  def WHEREIS(test:String) = {
    val url = "https://pokeapi.co/api/v2/"
    val http = url + "pokemon/" + test + "/encounters"
    val result = scala.io.Source.fromURL(http).mkString
    println(result)
  }

  def MOVE(test: String) = {
    val url = "https://pokeapi.co/api/v2/"
    val http = url + "move/" + test
    val result = scala.io.Source.fromURL(http).mkString
    println(result)
  }

  def commandLoop(pokemons : List[Pokemon]): Unit = {
    println("\nPlease enter a command [Respect the case between <>] (GET <Pokemon Name> , GETALL <Type>, GETALLGENERATION <Gen Number>, GETALLLEGENDARY <True/False>,\n COUNT <Type>, MATCH <Type>, WHEREIS <pokemon>, MOVE <move-name> QUIT):\n")
    val a = scala.io.StdIn.readLine()
    var args = a.split(" ").toList
    args match {
      case List("GET", pokemonName) => println("\n" + pokemonName + "'s info :\n" + getPokemon(args(1), pokemons))
      case List("GETALL", pokemonType) => getAllPokemonFromType(args(1), pokemons).foreach(println)
      case List("GETALLGENERATION", pokemonType) => getAllPokemonFromGeneration(args(1).toInt, pokemons).foreach(println)
      case List("GETALLLEGENDARY", pokemonType) => getAllPokemonFromLegendary(args(1), pokemons).foreach(println)
      case List("COUNT", pokemonType) => println("\nThere is " + countPokemonFromType(args(1), pokemons) + " Pokemons of that type")
      case List("MATCH", pokemonType) => matchPokemon(args(1), pokemons).foreach(println)
      case List("WHEREIS", pokemonName) => WHEREIS(pokemonName)
      case List("MOVE", moveName) => MOVE(moveName)
      case List("QUIT") => System.exit(0)
      case _ => println("Commande inconnue")
    }
    commandLoop(pokemons)
  }

  def main(args: Array[String]): Unit =
    println("\n**********************************************************\n")
    println("_______________Welcome to the almost finished Pokedex__________________\n")
    println("**********************************************************")
    var getPokemons = readCSV.andThen(parsePokemons)
    val pokemons = getPokemons("src/main/resources/pokemon.csv")
    pokemons match {
      case Right(pokemons) => commandLoop(pokemons)
      case Left(err) => println(err)
    }