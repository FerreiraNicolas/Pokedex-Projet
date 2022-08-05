package scala.fplesson3.models

import java.net.URL

final case class PokemonEntry(
  name: String,
  url: String
)

final case class PokemonList(
  count: Int,
  next: Option[String],
  results: Seq[PokemonEntry]
)