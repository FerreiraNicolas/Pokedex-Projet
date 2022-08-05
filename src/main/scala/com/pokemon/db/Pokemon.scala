package com.pokemon.db

case class Pokemon (
  id: Int,
  name: String,
  type1: String,
  type2: String,
  total: Int,
  hp: Int,
  attack: Int,
  defense: Int,
  spAttack: Int,
  spDefense: Int,
  speed: Int,
  generation: Int,
  legendary: Boolean
){
    override def toString: String = {
      s"-------\nid: $id,\nName: $name,\nType 1: $type1,\nType 2: $type2,\nTotal: $total,\nhp: $hp,\nAttack: $attack,\nDefense: $defense,\nspAttack: $spAttack,\nspDefense: $spDefense,\nSpeed: $speed,\nGeneration: $generation,\nLegendary: $legendary\n-------"
    }
}

case class PokemonError(
    message: String
)