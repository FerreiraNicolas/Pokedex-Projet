package scala.fplesson3.json

import spray.json._
import DefaultJsonProtocol._

import scala.fplesson3.models.{PokemonList, Pokemon, PokemonEntry}

object Decoders {
  implicit val pokemonEntry: JsonFormat[PokemonEntry] = jsonFormat2(PokemonEntry.apply)
  implicit val pokemonList: JsonFormat[PokemonList] = jsonFormat3(PokemonList.apply)
  implicit val pokemon: JsonFormat[Pokemon] = jsonFormat5(Pokemon.apply)
}
