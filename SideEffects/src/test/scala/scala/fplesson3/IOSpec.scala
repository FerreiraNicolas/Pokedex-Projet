package scala.fplesson3

import org.scalatest.funsuite.AnyFunSuite
import monix.execution.Scheduler.Implicits.global
import monix.eval.Task

class IOSpec extends AnyFunSuite {


  // test("Monix task") {
  //   Task(throw new IllegalArgumentException("disk error"))
  //     .flatMap(_ => Task(throw new Exception("bad network")))
  //     .onErrorRecoverWith {
  //       case ex: IllegalArgumentException =>
  //         Task(println("hello world"))
  //     }
  //     .runAsync {
  //       case Right(_) => println("Success")
  //       case Left(ex) => ex.printStackTrace
  //     }
  // }

  test("Get pokemons") {
    PokeAPI.getAll().runAsync {
      case Right(pokemons) => println(pokemons)
      case Left(ex) => ex.printStackTrace()
    }
  }

  // test("Next") {
  //   val get40Pokemons = for {
  //     firstEntries <- PokeAPI.getAll()
  //     optNextEntries <- PokeAPI.next(firstEntries)
  //   } yield {
  //     optNextEntries.fold(firstEntries.results)(nextEntries => firstEntries.results ++ nextEntries.results)
  //   }

  //   get40Pokemons.runAsync {
  //     case Right(pokemons) => println(pokemons)
  //     case Left(ex) => ex.printStackTrace()
  //   }
    
  // }

  // test("HTTP") {

  //   Http.request("https://www.google.fr")
  //     .runAsync {
  //       case Right(content) => println(content)
  //       case Left(ex) => ex.printStackTrace()
  //     }
  // }
  
}
