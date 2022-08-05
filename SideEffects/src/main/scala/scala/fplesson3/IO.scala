package scala.fplesson3

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.util.control.NonFatal

case class IO[A](run: () => A) {

  def map[B](f: A => B): IO[B] =
    IO(() => f(this.run()))

  def flatMap[B](f: A => IO[B]): IO[B] =
    IO(() => f(this.run()).run())

  def unsafeSyncRun(): A =
    this.run()

  def unsafeAsyncRun()(using ec: ExecutionContext): Future[A] =
    Future {
      this.run()
    }

  def recoverWith[E <: Throwable, B >: A](f: Throwable => IO[B]): IO[B] =
    IO(() => {
      try {
        this.run()
      } catch {
        case ex: Throwable =>
          f(ex).run()
      }
    })
}

object IO {

  def testIO(): Unit = {
    def readLine() = IO(() => scala.io.StdIn.readLine())
    def printLine[A](a: A) = IO(() => println(a))


    val prg = for {
      line <- readLine()
      _ <- printLine(line)
      line2 <- readLine()
      line3 <- readLine()
    } yield ()

    prg.unsafeSyncRun()
  }
}
