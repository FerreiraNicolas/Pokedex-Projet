package scala.fplesson3

import monix.eval.Task
import scala.fplesson3.models.PokemonList
import scala.fplesson3.json.Decoders._
import spray.json._

object PokeAPI {

  def getAll(): Task[PokemonList] = {
    Http.request("https://pokeapi.co/api/v2/pokemon/")
      .map(json => json.parseJson.convertTo[PokemonList])
  }

  def next(pokemonList: PokemonList): Task[Option[PokemonList]] =
    pokemonList.next.fold(
      Task(None)
    )(
       next => Http.request(next)
        .map(json => json.parseJson.convertTo[PokemonList])
        .map(Some.apply),
    )

  
}
